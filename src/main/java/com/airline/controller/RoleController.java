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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.airline.entity.Roles;
import com.airline.serviceImpl.RoleServiceImpl;

@Controller
@RequestMapping("/role")
public class RoleController {

	
	@Autowired
	RoleServiceImpl roleserviceimpl;
	
	@GetMapping(value="/role_creation")
	public String Role_Creation() {
		return "role_creation";
	}
	
	
	@PostMapping(value="/saveRole")
	public String saveRole(@ModelAttribute("role") Roles role,Model model,RedirectAttributes rd) {
		String message="";
		Roles roles=roleserviceimpl.saveRole(role);
		if(roles.getRoleId() != null) {
			message="Role Created Success Fully";
			rd.addFlashAttribute(message, roles);
		}else {
			message="Unable to create Role.Please Try Again!!!!";
			rd.addFlashAttribute(message, roles);
		}
		model.addAttribute("message", message);
		rd.addFlashAttribute(message, roles);
		return "redirect:/role/list";
	}
	
	
	@GetMapping("/list")
	private String list(Model model) {
		List<Roles> list = roleserviceimpl.findByIsActive();
		System.out.println(list.size());
		if (list.isEmpty()) {
			return "redirect:/role/role_creation";
		} else {
			model.addAttribute("list", list);
			return "roles_list";
		}
	}
	
	@GetMapping("/edit")
	public String edit(String id, Model model) {
		Roles role = roleserviceimpl.getRoleById(id);
		model.addAttribute("role", role);
		return "role_creation";
	}
	
	@PostMapping(value = "/delete")
	public String delete(@RequestParam("id") String id) {
		Roles emp = roleserviceimpl.delete(id);
		return "redirect:list";
	}
}
