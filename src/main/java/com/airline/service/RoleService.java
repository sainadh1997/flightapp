package com.airline.service;

import java.util.List;

import com.airline.entity.Roles;

public interface RoleService {

	Roles saveRole(Roles role);
	List<Roles> rolesList();
	
	Roles delete(String id);
	Roles getRoleById(String id);
	List<Roles> findByIsActive();
}
