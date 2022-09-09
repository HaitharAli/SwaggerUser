package com.user.swagger.repository;

import com.user.swagger.entity.User;

public interface CustomUserRepository {

	User customFindMethod(Long id);
}
