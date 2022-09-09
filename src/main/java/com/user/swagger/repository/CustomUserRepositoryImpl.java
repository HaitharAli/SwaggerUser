package com.user.swagger.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.user.swagger.entity.User;

public class CustomUserRepositoryImpl implements CustomUserRepository{
	
	@PersistenceContext
    private EntityManager entityManager;

    @Override
    public User customFindMethod(Long id) {
        return (User) entityManager.createQuery("FROM User u WHERE u.id = :id")
          .setParameter("id", id)
          .getSingleResult();
    }

}
