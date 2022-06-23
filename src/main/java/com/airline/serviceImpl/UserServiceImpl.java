package com.airline.serviceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.airline.entity.Users;
import com.airline.repository.UserRepository;
import com.airline.service.UserService;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepo;
	
	
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	 
	
	
	
	
	
	@Override
	public Users uniqueusernameNoCheck(String username) {
		return userRepo.findByUsername(username);
	}

	@Override
	@Transactional
	public Users saveUserInfo(Users user) {
		java.sql.Date sqlStartDate = null;
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		try {
			java.util.Date date = sdf1.parse(user.getDOB());
			sqlStartDate= new java.sql.Date(date.getTime());  
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String randomPassword = user.getPassword();
		user.setPassword(passwordEncoder.encode(randomPassword));
		
		
		
		user.setDateofBirth(sqlStartDate);
		Users usr=userRepo.save(user);
		/*
		 * if (usr.getUserid() != null &&
		 * "on".equalsIgnoreCase(user.getUserasCaptain())) { Captain captain=new
		 * Captain(); captain.setCaptainFirstName(user.getFirstName());
		 * captain.setCaptainLastName(user.getLastName());
		 * captain.setGender(user.getGender()); captain.setDateofBirth(sqlStartDate);
		 * captain.setUserid(usr.getUserid()); Captain capt=capRepo.save(captain); }
		 */
		return usr;
	}

	@Override
	public List<Users> usersList() {
		return userRepo.findAll();
	}

	@Override
	public Users findByUserName(String username) {
		// TODO Auto-generated method stub
		return userRepo.findByUsername(username);
	}

	@Override
	public Users delete(String id) {
		Users user = userRepo.findByUserid(Long.parseLong(id));
		user.setIsActive(false);
		userRepo.save(user);
		return user;
	}

	@Override
	public Users getUserById(String id) {
		//BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		Users user=userRepo.findByUserid(Long.parseLong(id));
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
		String date = DATE_FORMAT.format(user.getDateofBirth());
		user.setDOB(date);
		//System.out.println(bcryptPasswordEncoder.de(user.getPassword()));
		//user.setPassword(bcryptPasswordEncoder.encode(user.getPassword()));
		return user;
	}

	@Override
	public List<Users> findByIsActive() {
		return userRepo.findByIsActive(true);
	}

	@Override
	public List<Users> captainsList(Boolean active, String role) {
		
		return userRepo.captainsList(active, role);
	}

}
