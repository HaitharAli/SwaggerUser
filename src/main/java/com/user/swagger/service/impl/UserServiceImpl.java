package com.user.swagger.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.user.swagger.dto.UserDto;
import com.user.swagger.dto.UserInputDto;
import com.user.swagger.entity.Role;
import com.user.swagger.entity.User;
import com.user.swagger.repository.UserPaginationRepository;
import com.user.swagger.repository.UserRepository;
import com.user.swagger.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserPaginationRepository userPaginationRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<UserDto> getAllUsers() {
		logger.info("UserServiceImpl::getAllUsers - Start");
		List<UserDto> userDetailsList = userRepository.findAll().stream().map(this::convertUserDto)
				.collect(Collectors.toList());
		logger.info("UserServiceImpl::getAllUsers - End");
		return userDetailsList;
	}

	private UserDto convertUserDto(User user) {
		logger.info("UserServiceImpl::convertUserDto - Start");
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		logger.info("UserServiceImpl::convertUserDto - End");
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto addUser(UserInputDto userInputDto, String loginUserId) {
		logger.info("UserServiceImpl::addUser - Start");
		User userDetails = modelMapper.map(userInputDto, User.class);
		userDetails.setCreatedBy(loginUserId);
		userDetails.setModifiedBy(loginUserId);

		List<Role> roles = userDetails.getUserRoles();
		roles.forEach(role -> {
			role.setCreatedBy(loginUserId);
			role.setModifiedBy(loginUserId);

		});

		userDetails.setUserRoles(userInputDto.getRoles());
		System.out.println(userDetails.getCreatedBy());

		User userDetailsList = userRepository.save(userDetails);
		logger.info("UserServiceImpl::addUser - End");
		return modelMapper.map(userDetailsList, UserDto.class);
	}

	@Override
	@Cacheable(key = "#id", value = "User")
	public Optional<UserDto> getUserById(Long id) {
		logger.info("UserServiceImpl::getUserById - Start");
		Optional<UserDto> userDetails = userRepository.findById(id).map(this::convertUserDto);
		logger.info("UserServiceImpl::getUserById - End");
		return userDetails;
	}

	@Override
	public List<UserDto> getUserSortedByFirstName() {
		logger.info("UserServiceImpl::getUserById - Start");
		List<UserDto> userDetails = userRepository.findByIdSortedByFirstName().stream()
				.map(this::convertUserDto).collect(Collectors.toList());
		logger.info("UserServiceImpl::getUserById - End");
		return userDetails;
	}

	@Override
	@Cacheable(key = "#id", value = "User")
	public UserDto customGetUserById(Long id) {
		logger.info("UserServiceImpl::getUserById - Start");
		// UserDto userDetails =
		// userRepository.customFindMethod(id).map(this::convertUserDto);
		UserDto userDetails = convertUserDto(userRepository.customFindMethod(id));
		logger.info("UserServiceImpl::getUserById - End");
		return userDetails;
	}

	@Override
	public UserDto updateUser(UserDto userDetails) {
		logger.info("UserServiceImpl::updateUser - Start");
		User userdt = modelMapper.map(userDetails, User.class);
		logger.info("UserServiceImpl::updateUser - End");
		return this.convertUserDto(userRepository.save(userdt));
	}

	@Override
	public Boolean deleteUser(UserDto userDetails) {
		logger.info("UserServiceImpl::deleteUser - Start");
		User userdt = modelMapper.map(userDetails, User.class);
		userRepository.delete(userdt);
		logger.info("UserServiceImpl::deleteUser - End");
		return true;
	}

	@Override
	public List<UserDto> getAllUsersPaginated(int pageNo, int pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);

		logger.info("UserServiceImpl::getAllUsersPaginated - Start");
		List<UserDto> userDetailsList = userPaginationRepository.findAll(paging).stream().map(this::convertUserDto)
				.collect(Collectors.toList());
		logger.info("UserServiceImpl::getAllUsersPaginated - End");
		return userDetailsList;
	}

	@Override
	public Optional<UserDto> getUserByEmailId(String emailId) {

		User user = userRepository.findByEmail("%" + emailId + "%");

		return Optional.of(modelMapper.map(user, UserDto.class));
	}

}
