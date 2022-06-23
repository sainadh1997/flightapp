package com.airline.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.airline.entity.Timetracking;
import com.airline.entity.Users;
import com.airline.serviceImpl.TimtrackingServiceImpl;
import com.airline.serviceImpl.UserServiceImpl;

@Controller
public class HomeController {
	
	@Autowired
	TimtrackingServiceImpl timetrackingImpl;
	
	@Autowired
	UserServiceImpl userserimpl;
	

	@RequestMapping(value={"/","/home"},method = RequestMethod.GET)
	public String home(HttpSession session,Model model) {
		System.out.println("Inside Home Method"+session.getAttribute("roleName"));
		System.out.println("Inside Home Method"+session.getAttribute("loginusername"));
		
		  String roleName=(String) session.getAttribute("roleName"); 
		  String loginUserName=(String) session.getAttribute("loginusername");
		  if("Captain".equalsIgnoreCase(roleName)){ 
			  Users user=userserimpl.findByUserName(loginUserName);
			  List<Timetracking> timetrackingList=timetrackingImpl.searchByCaptainId(String.valueOf(user.getUserid()));
			  System.out.println(timetrackingList.size());
			  model.addAttribute("timetrackingList", timetrackingList);
			  }
		 
		model.addAttribute("roleName", session.getAttribute("roleName"));
		return "dashboard";
	}
	
	
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	@ExceptionHandler(value = Exception.class)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletResponse response)
			throws IOException {
		if (response == null) {
			System.out.println("Invalid username and password!" + response.getStatus());
		}
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("errorMsg", "Invalid username or password");
			System.out.println("Invalid username and password!");
		}
		if (logout != null) {
			model.addObject("errorMsg", "You've been logged out successfully.");
			System.out.println("You've been logged out successfully.");
		}
		model.setViewName("login");
		return model;
	}
	
	
	
	@RequestMapping("/accessdenied")
	public ModelAndView errorPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("accessdenied");
		return model;
	}
	
}
