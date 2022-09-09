package com.user.swagger.dto;

import java.io.Serializable;
import java.util.List;

import com.user.swagger.entity.AbstractEntity;
import com.user.swagger.entity.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = -1530311220388659890L;
	private Integer userId;
	private String firstName;
	private String lastName;
	private String gender;
	private String email;
	private List<Role> userRoles;

}
