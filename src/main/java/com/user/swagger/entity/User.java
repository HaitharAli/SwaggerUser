package com.user.swagger.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Table(name = "user")
@Data
@EqualsAndHashCode(callSuper = false)
@NamedQuery(name="User.findByEmail" ,query = "select u from User u where u.email like ?1")
public class User extends AbstractEntity  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	private String gender;
	private String email;
	@OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name = "userId")
	private List<Role> userRoles;

}
