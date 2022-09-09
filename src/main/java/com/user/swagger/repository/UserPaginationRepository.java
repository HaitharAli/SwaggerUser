package com.user.swagger.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.user.swagger.entity.User;

public interface UserPaginationRepository extends PagingAndSortingRepository<User, Long>{

}
