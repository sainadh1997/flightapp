package com.airline.service;

import java.util.List;

import com.airline.entity.Users;

public interface UserService {

	Users uniqueusernameNoCheck(String username);
	Users saveUserInfo(Users user);
	List<Users> usersList();
	Users findByUserName(String username);
	
	Users delete(String id);
	Users getUserById(String id);
	List<Users> findByIsActive();
	List<Users> captainsList(Boolean active,String role);
	
}
