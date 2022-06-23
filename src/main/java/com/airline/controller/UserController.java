package com.airline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.airline.entity.Roles;
import com.airline.entity.Users;
import com.airline.serviceImpl.RoleServiceImpl;
import com.airline.serviceImpl.UserServiceImpl;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	RoleServiceImpl roleservImpl;
	
	@Autowired
	UserServiceImpl userservImpl;
	
	
	@GetMapping("/user_creation")
	public String usercreation(Model model) {
		List<Roles> rolesList=roleservImpl.findByIsActive();
		model.addAttribute("rolesList", rolesList);
		return "user_creation";
	}
	
	
	@PostMapping(value="/saveUserInfo")
	public String saveUserInfo(@ModelAttribute("userinfo") Users user,Model model) {
		String message="";
		Users usr=userservImpl.saveUserInfo(user);
		if (usr.getUserid() != null) {
			message="User Created SuccessFully";
		}else {
			message="Unable to Create User.Please Try Again!!!";
		}
		model.addAttribute("message", message);
		return "redirect:/user/list";	
	}
	
	@GetMapping(value = "/isUniqueusername")
	@ResponseBody
	public boolean uniqueusernameNoCheck(String username) {
		Users user = userservImpl.uniqueusernameNoCheck(username);
		if (user != null) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
	@GetMapping("/list")
	private String list(Model model) {
		System.out.println("inside users list method");
		List<Users> list = userservImpl.findByIsActive();
		if (list.isEmpty()) {
			return "redirect:/user/user_creation";
		} else {
			model.addAttribute("list", list);
			return "users_list";
		}
	}
	
	
	@GetMapping("/edit")
	public String edit(String id, Model model) {
		Users user = userservImpl.getUserById(id);
		model.addAttribute("userinfo", user);
		List<Roles> rolesList=roleservImpl.findByIsActive();
		model.addAttribute("rolesList", rolesList);
		return "user_creation";
	}
	
	@PostMapping(value = "/delete")
	public String delete(@RequestParam("id") String id) {
		Users emp = userservImpl.delete(id);
		return "redirect:list";
	}


}
