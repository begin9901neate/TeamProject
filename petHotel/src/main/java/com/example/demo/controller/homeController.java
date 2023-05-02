package com.example.demo.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class homeController {
	
	/******************* forestage ****************************/	
	@GetMapping({"/","/home"})
    public ModelAndView homePage(ModelMap map) {
        return new ModelAndView("forestage/catHome");
    }
	
	@GetMapping("/login")
    public ModelAndView loginPage(ModelMap map) {
        return new ModelAndView("login");
    }
	
	@GetMapping("/signup")
    public ModelAndView signupPage(ModelMap map) {
        return new ModelAndView("signup");
    }
	
	@GetMapping("/rooms")
    public ModelAndView roomsPage(ModelMap map) {
        return new ModelAndView("forestage/catRooms");
    }
	
	@GetMapping("/blank")
    public ModelAndView blankPage(ModelMap map) {
        return new ModelAndView("forestage/catBlank");
    }
	
	/******************* admin ****************************/	
	@GetMapping("/adminBlank")
    public ModelAndView adminBlankPage(ModelMap map) {
        return new ModelAndView("admin/adminBlank");
    }
	
	@GetMapping("/adminReport")
    public ModelAndView adminReportPage(ModelMap map) {
        return new ModelAndView("admin/adminReport");
    }
	
	@GetMapping("/adminUsers")
    public ModelAndView adminUsersPage(ModelMap map) {
        return new ModelAndView("admin/adminUsers");
    }
	
	@GetMapping("/adminRooms")
    public ModelAndView adminRoomsPage(ModelMap map) {
        return new ModelAndView("admin/adminRooms");
    }
	
	/******************* hao yuan ****************************/
	@RequestMapping("/backstage")
    public ModelAndView backstagePage(ModelMap map) {
        return new ModelAndView("backstage/index");
    }
	
	@RequestMapping("/backstage/user")
	@CrossOrigin("http://localhost/petHotel")
    public ModelAndView backstageUserPage(ModelMap map) {
        return new ModelAndView("backstage/user");
    }
	
	@RequestMapping("/backstage/commodity")
	@CrossOrigin("http://localhost/petHotel")
    public ModelAndView backstageCommodityPage(ModelMap map) {
        return new ModelAndView("backstage/commodity");
    }
	
	@RequestMapping("/backstage/commodity_order")
	@CrossOrigin("http://localhost/petHotel")
    public ModelAndView backstageCommodityOrderPage(ModelMap map) {
        return new ModelAndView("backstage/commodity_order");
    }
	
	@RequestMapping("/backstage/room")
	@CrossOrigin("http://localhost/petHotel")
    public ModelAndView backstageRoomPage(ModelMap map) {
        return new ModelAndView("backstage/room");
    }
	
	@RequestMapping("/backstage/room_order")
	@CrossOrigin("http://localhost/petHotel")
    public ModelAndView backstageRoomOrderPage(ModelMap map) {
        return new ModelAndView("backstage/room_order");
    }
	
	@RequestMapping("/backstage/response")
	@CrossOrigin("http://localhost/petHotel")
    public ModelAndView backstageResponsePage(ModelMap map) {
        return new ModelAndView("backstage/response");
    }
	

}
