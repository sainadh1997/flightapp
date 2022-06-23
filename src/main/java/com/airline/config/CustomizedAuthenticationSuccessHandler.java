
package com.airline.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class CustomizedAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired
	HttpSession session;

	public CustomizedAuthenticationSuccessHandler() {
		// TODO Auto-generated constructor stub
		super();

	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException { // TODO Auto-generated method stub
		CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
		session.setAttribute("user", user.getAuthorities());
		session.setAttribute("loginusername", user.getUsername());
		session.setAttribute("roleName", user.getUserRole());
		session.setAttribute("userid", user.getUserId());
		System.out.println(user.getUserRole()); // Employee employee =
		//employeeService.getEmployeeById(user.getUserId()); // Role employeeRole = new
		//Role(); // employeeRole.setName(user.getUserRole());

		/*
		 * Optional<Role> employeeWithRoleUser =
		 * employee.getRoles().stream().filter(role -> role.getName().equals("USER"))
		 * .findAny(); if (employeeWithRoleUser.isPresent()) {
		 * session.setAttribute("isExperienceEmployee",
		 * employee.getEmpCategory().equals("E"));
		 * session.setAttribute("employeeFirstLogin", employee.getIsFirsttime()); }
		 */

		// this.formFeedStatusService.setFormFeedStatus(null);
		super.onAuthenticationSuccess(request, response, authentication);
	}

}
