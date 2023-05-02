package com.example.demo.controller;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Branch;
import com.example.demo.model.OrderRooms;
import com.example.demo.model.Rooms;
import com.example.demo.model.Users;
import com.example.demo.service.BranchService;
import com.example.demo.service.RoomsService;

//@RestController
////@RequestMapping(value = "/rooms")
//public class RoomsController {
//	
//	@Autowired
//	private BranchService branchService;
//	
//	@Autowired
//	private RoomsService roomsService;
//	
//	@GetMapping({"/findRooms"})
//	public ModelAndView findRooms() {
//
//		ModelAndView model = new ModelAndView("ianTest/findRooms");
//		
//		Iterable<Branch> branches = branchService.getBranches();
//		model.addObject("branches", branches);
//		
//		Branch branch = branches.iterator().next();
//		model.addObject("branch", branch);
//		
//		Iterable<Rooms> roomses = roomsService.getRoomses();
//		model.addObject("roomses", roomses);
//		
//		return model;
//	}
//	
//	@PostMapping({"/findRoomsByBranch"})
//	public ModelAndView findRoomsByBranch(
//		@RequestParam(value = "id", required = false, defaultValue = "1") Integer id) {
//		
//		ModelAndView model = new ModelAndView("ianTest/findRooms");
//		
//		Iterable<Branch> branches = branchService.getBranches();
//		model.addObject("branches", branches);
//		
//		Branch branch = branchService.getBranchById(id);
//		
//		model.addObject("branch", branch);
//		model.addObject("roomses", branch.getRoomses());
//		
//		return model;
//	}
//	
//	/*@
//	public ModelAndView adminOrderRooms() {
//
//		ModelAndView model = new ModelAndView("admin/adminOrderRooms");
//		// 搜尋全部房型
//		Iterable<Rooms> roomses = roomsService.getRoomses();
//		model.addObject("roomses", roomses);
//		// 房型選單內容
//		Rooms rooms = roomses.iterator().next();
//		model.addObject("rooms", rooms);
//		
//		// 搜尋全部使用者
//		Iterable<Users> userses = usersService.getUserses();
//		model.addObject("userses", userses);
//		// 使用者選單內容
//		Users users = userses.iterator().next();
//		model.addObject("users", users);
//		
//		// 搜尋全部 Room Order
//		Iterable<OrderRooms> orderRooms = orderRoomsService.getOrderRoomses();
//		model.addObject("orderRooms", orderRooms);
//		
//		return model;
//	}*/
//    
//	@GetMapping("/rooms")
//    public String showRoomOptions(Model model) {
//        // create model attribute to bind form data
//    	Integer id;
//        model.addAttribute("TaipeiRoom", roomsService.findByBranchId(1));
//        model.addAttribute("TaichungRoom", roomsService.findByBranchId(2));
//        System.out.println("test1"+roomsService.findByBranchId(1));
//        System.out.println("test2"+roomsService.findByBranchId(2));
//        
//        return "forestage/catRooms";
//    }
//    
//    @GetMapping("/showRoomAll")
//    public String showRoomAll(Model model) {
//        // create model attribute to bind form data
//    	model.addAttribute("listRoom", roomsService.getAllRoom());
//        return "showRoomAll";
//    }
//    
//    @GetMapping("/new_Room")
//    public String newRoom(Model model) {
//        // create model attribute to bind form data 
//    	model.addAttribute("branch", branchService.getAllBranch());
//        return "new_Room";
//    }
//    
//    @PostMapping("/saveRoom")
//    public String saveRoom(@RequestParam("BranchName") String branchId,
//            @RequestParam("RoomName") String roomName,
//            @RequestParam("max_pet_number") int maxPetNumber,
//            @RequestParam("RoomSize") String roomSize,
//            @RequestParam("equipment") String equipment,
//            @RequestParam("price") int price,
//            @RequestParam("note") String note, Model model) {
//    	Rooms room=new Rooms();
//    	
//    	LocalDateTime now = LocalDateTime.now();
//    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//    	String formattedDateTime = now.format(formatter);
//        Timestamp ts = Timestamp.valueOf(formattedDateTime);
//    	
//    	Branch Branch=branchService.getBranchById(Integer.parseInt(branchId));
//    	room.setBranch(Branch);
//    	room.setMaxPetNumber(maxPetNumber);
//    	room.setName(roomName);
//    	room.setSize(roomSize);
//    	room.setEquipment(equipment);
//    	room.setPrice(price);
//    	room.setNote(note);
//    	room.setCreatedAt(ts);
//        roomsService.saveRoom(room);
//        model.addAttribute("listRoom", roomsService.getAllRoom());
//    	/*String n="這是";
//    	System.out.println(n+branchId);
//    	System.out.println(n+roomName);
//    	System.out.println(n+maxPetNumber);
//    	System.out.println(n+roomSize);
//    	System.out.println(n+equipment);
//    	System.out.println(n+price);
//    	System.out.println(n+note);
//    	System.out.println(n+formattedDateTime);*/
//        return "showRoomAll";
//    } 
//    
//    @RequestMapping(value = "/RoomUpdate", method = RequestMethod.GET)
//    public ModelAndView openFormUpdate(@RequestParam(value="id", required=false, defaultValue="1") Long id) {
//       ModelAndView model = new ModelAndView("RoomUpdate");
//       Rooms room=roomsService.getRoomById(Integer.parseInt(id+""));
//       List<Branch> branch=branchService.getAllBranch();
//       model.addObject("room",room);
//       model.addObject("branch",branch);
//       return model;
//    }
//    
//    @RequestMapping(value = "/RoomUpdate", method = RequestMethod.POST)
//    public ModelAndView processFormUpdate(@ModelAttribute Rooms room, @RequestParam("BranchName") String branchId) throws SQLException {
//       ModelAndView model = new ModelAndView("redirect:/showRoomAll");
//       Branch Branch=branchService.getBranchById(Integer.parseInt(branchId));
//       room.setBranch(Branch);
//   	
//       LocalDateTime now = LocalDateTime.now();   	
//       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//       String formattedDateTime = now.format(formatter);
//   	   Timestamp ts = Timestamp.valueOf(formattedDateTime);
//   	   room.setCreatedAt(ts);   
//   	roomsService.saveRoom(room);
//       System.out.println("aaa"+branchId);
//       return model;
//    }
//    
//    @RequestMapping(value = "/RoomDelete", method = RequestMethod.GET)
//    public ModelAndView deleteFormUpdate(@RequestParam(value="id", required=false, defaultValue="1") Long id) {
//       ModelAndView model = new ModelAndView("showRoomAll");
//       Rooms room=roomsService.getRoomById(Integer.parseInt(id+""));
//       
//       LocalDateTime now = LocalDateTime.now();   	
//       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//       String formattedDateTime = now.format(formatter);
//   	   Timestamp ts = Timestamp.valueOf(formattedDateTime);
//   	   room.setDeletedAt(ts); 
//   	roomsService.saveRoom(room);
//   	   List<Rooms> roomall=roomsService.getAllRoom();
//   	   model.addObject("listRoom",roomall);
//       return model;
//    }
//}
