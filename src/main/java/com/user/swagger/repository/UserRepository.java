package com.user.swagger.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.user.swagger.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>,CustomUserRepository{
	
	User findByEmail(String email);
	
	@Query(value = "select * from user order by first_name", nativeQuery = true)
	List<User> findByIdSortedByFirstName();

}
