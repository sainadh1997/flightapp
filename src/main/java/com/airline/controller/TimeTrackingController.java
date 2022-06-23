package com.airline.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.airline.entity.Timetracking;
import com.airline.entity.Users;
import com.airline.serviceImpl.TimtrackingServiceImpl;
import com.airline.serviceImpl.UserServiceImpl;
import com.airline.util.XlsxGeneration;

@Controller
@RequestMapping("/timetracking")
public class TimeTrackingController {

	
	@Autowired
	TimtrackingServiceImpl timetrackingImpl;
	
	
	/*
	 * @Autowired CaptainServiceImpl captainServiceImpl;
	 */
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@RequestMapping(value="/time_sheet_entry_form",method = RequestMethod.GET)
	public String timesheetentryform(Model model) {
		Boolean active=true;
		String role="Captain";
		List<Users> captainList=userServiceImpl.captainsList(active,role);
		model.addAttribute("captainList", captainList);
		return "time_sheet_entry_form";
	}
	
	@PostMapping(value = "/saveTimeTracking")
	public String saveTimeEntry(@ModelAttribute("timetracking") Timetracking timetracking,Model model) {
		System.out.println("inside Save method");
		System.out.println("date"+timetracking.getDutyDate());
		String respMsg=timetrackingImpl.SaveTimetracking(timetracking);
		model.addAttribute("respMsg", respMsg);
		return "redirect:/timetracking/list";
	}
	
	
	@GetMapping("/list")
	private String list(Model model) {
		List<Timetracking> list = timetrackingImpl.findByIsActive();
		if (list.isEmpty()) {
			return "redirect:/timetracking/time_sheet_entry_form";
		} else {
			System.out.println(list.size());
			model.addAttribute("list", list);
			return "time_tracking_list";
		}
	}
	
	
	@GetMapping("/edit")
	public String edit(String id, Model model) {
		Timetracking timetracking = timetrackingImpl.getTimetrackingById(id);
		model.addAttribute("timetracking", timetracking);
		Boolean active=true;
		String role="Captain";
		List<Users> captainList=userServiceImpl.captainsList(active,role);
		model.addAttribute("captainList", captainList);
		return "time_sheet_entry_form";
	}
	
	@PostMapping(value = "/delete")
	public String delete(@RequestParam("id") String id) {
		Timetracking timetracking = timetrackingImpl.delete(id);
		return "redirect:list";
	}

	
	@RequestMapping(value="/report",method = RequestMethod.GET)
	public String report(Model model,HttpSession session) {
		String loginusername=(String) session.getAttribute("loginusername");
		String roleName=(String)session.getAttribute("roleName");
		String userid=(String)session.getAttribute("userid");
		
		Boolean active=true;
		String role="Captain";
		List<Users> captainList=userServiceImpl.captainsList(active,role);
		model.addAttribute("captainList", captainList);
		model.addAttribute("loginusername", loginusername);
		model.addAttribute("roleName", roleName);
		model.addAttribute("userid",userid);
		return "time_tracking_report";
	}
	
	@RequestMapping(value="/search",method = RequestMethod.GET)
	@ResponseBody
	public List<Timetracking> search(Model model,String captainId,String search_from_date,String search_to_date,HttpSession session) {
		List<Timetracking> searchList=timetrackingImpl.search(captainId, search_from_date,search_to_date);
		System.out.println(searchList.size());
		model.addAttribute("searchList", searchList);
		session.setAttribute("searchList", searchList);
		return searchList;
	}
	
	
	
	@GetMapping("/downloadTimesheetExcel")
    public void exportToExcel(HttpServletResponse response,HttpSession session) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=timetrackingReport_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
         
        
         
        XlsxGeneration excelExporter = new XlsxGeneration();
         
        try {
			excelExporter.export(response,(List<Timetracking>)session.getAttribute("searchList"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
    }   
	
	
	
	
}
