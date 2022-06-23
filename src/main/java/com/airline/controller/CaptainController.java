package com.airline.controller;
/*
 * package com.flightdata.controller;
 * 
 * import java.util.List;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.ui.Model; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.ModelAttribute; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RequestParam;
 * 
 * import com.flightdata.entity.Captain; import
 * com.flightdata.serviceImpl.CaptainServiceImpl;
 * 
 * @Controller
 * 
 * @RequestMapping("/captain") public class CaptainController {
 * 
 * @Autowired CaptainServiceImpl capserviceImpl;
 * 
 * @GetMapping(value="/captain_creation") public String captainCreation() {
 * return "captain_creation"; }
 * 
 * 
 * @PostMapping(value="/saveCaptainInfo") public String
 * saveCaptainInfo(@ModelAttribute("captain") Captain captain,Model model) {
 * String message=""; Captain capt=capserviceImpl.saveCaptainInfo(captain);
 * System.out.println(capt.getCaptainId()); if (capt.getCaptainId() != null) {
 * message="Captain Created SuccessFully"; }else {
 * message="Unable to Create Captainr.Please Try Again!!!"; }
 * model.addAttribute("message", message); return "redirect:/captain/list"; }
 * 
 * 
 * @GetMapping("/list") private String list(Model model) { List<Captain> list =
 * capserviceImpl.findByIsActive(); System.out.println(list.size()); if
 * (list.isEmpty()) { return "redirect:/captain/captain_creation"; } else {
 * model.addAttribute("list", list); return "captains_List"; } }
 * 
 * 
 * 
 * @GetMapping("/edit") public String edit(String id, Model model) { Captain
 * captain = capserviceImpl.getCaptainById(id); model.addAttribute("captain",
 * captain); return "captain_creation"; }
 * 
 * @PostMapping(value = "/delete") public String delete(@RequestParam("id")
 * String id,Model model) {
 * 
 * Captain captain = capserviceImpl.delete(id); model.addAttribute("captain",
 * captain); return "redirect:list"; }
 * 
 * 
 * 
 * 
 * 
 * }
 */