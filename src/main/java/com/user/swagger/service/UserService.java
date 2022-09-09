package com.user.swagger.service;

import java.util.List;
import java.util.Optional;

import com.user.swagger.dto.UserDto;
import com.user.swagger.dto.UserInputDto;

public interface UserService {

	List<UserDto> getAllUsers();

	UserDto addUser(UserInputDto userInputDto, String loginUserId);

	Optional<UserDto> getUserById(Long id);

	UserDto updateUser(UserDto userDetails);

	Boolean deleteUser(UserDto userDetails);

	List<UserDto> getAllUsersPaginated(int pageNo,int pageSize);

	Optional<UserDto> getUserByEmailId(String emailId);

	UserDto customGetUserById(Long id);

	List<UserDto> getUserSortedByFirstName();

}
