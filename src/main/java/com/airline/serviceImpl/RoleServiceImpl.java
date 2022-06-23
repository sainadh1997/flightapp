package com.airline.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airline.entity.Roles;
import com.airline.repository.RoleRepository;
import com.airline.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	RoleRepository roleRepo;
	
	@Override
	public Roles saveRole(Roles role) {
		// TODO Auto-generated method stub
		return roleRepo.save(role);
	}

	@Override
	public List<Roles> rolesList() {
		
		return roleRepo.findAll();
	}

	
	@Override
	public List<Roles> findByIsActive() {
		return roleRepo.findByIsActive(true);
	}
	
	@Override
	public Roles delete(String id) {
		Roles role = roleRepo.findByRoleId(Long.parseLong(id));
		role.setIsActive(false);
		roleRepo.save(role);
		return role;
	}

	@Override
	public Roles getRoleById(String id) {
		return roleRepo.findByRoleId(Long.parseLong(id));
	}
}
