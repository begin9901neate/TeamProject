package com.example.demo.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.BranchService;
import com.example.demo.service.OrderRoomsService;
import com.example.demo.model.Rooms;
import com.example.demo.model.Branch;
import com.example.demo.service.RoomsService;

import java.text.SimpleDateFormat;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@Controller
public class OrderRoomController {

    @Autowired
    private OrderRoomsService orderRoomService;
    
    @Autowired
    private RoomsService RoomService;
    
    @Autowired
    private BranchService BranchService;

    // display list of OrderRoom
//    @GetMapping("/")
//    public String viewHomePage(Model model) {
//        model.addAttribute("listOrderRoom", orderRoomService.getOrderRoomses());
//        model.addAttribute("listRoom", RoomService.getAllRoom());
//        return "index";
//    }
   
    @GetMapping("/showRoom")
    public String showRoomOptions(Model model) {
        // create model attribute to bind form data
    	Integer id;
        model.addAttribute("TaipeiRoom", RoomService.findByBranchId(1));
        model.addAttribute("TaichungRoom", RoomService.findByBranchId(2));
        return "ShowRoom";
    }
    
    @GetMapping("/showRoomAll")
    public String showRoomAll(Model model) {
        // create model attribute to bind form data
    	model.addAttribute("listRoom", RoomService.getAllRoom());
        return "showRoomAll2";
    }
    
    @GetMapping("/new_Room")
    public String newRoom(Model model) {
        // create model attribute to bind form data 
    	model.addAttribute("branch", BranchService.getAllBranch());
        return "new_Room2";
    }
    
    @PostMapping("/saveRoom")
    public String saveRoom(@RequestParam("BranchName") String branchId,
            @RequestParam("RoomName") String roomName,
            @RequestParam("max_pet_number") int maxPetNumber,
            @RequestParam("RoomSize") String roomSize,
            @RequestParam("equipment") String equipment,
            @RequestParam("price") int price,
            @RequestParam("note") String note, Model model) {
    	Rooms room=new Rooms();
    	
    	LocalDateTime now = LocalDateTime.now();
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    	String formattedDateTime = now.format(formatter);
        Timestamp ts = Timestamp.valueOf(formattedDateTime);
    	
    	Branch Branch=BranchService.getBranchById(Integer.parseInt(branchId));
    	room.setBranch(Branch);
    	room.setMaxPetNumber(maxPetNumber);
    	room.setName(roomName);
    	room.setSize(roomSize);
    	room.setEquipment(equipment);
    	room.setPrice(price);
    	room.setNote(note);
    	room.setCreatedAt(ts);
        RoomService.saveRoom(room);
        model.addAttribute("listRoom", RoomService.getAllRoom());
    	/*String n="這是";
    	System.out.println(n+branchId);
    	System.out.println(n+roomName);
    	System.out.println(n+maxPetNumber);
    	System.out.println(n+roomSize);
    	System.out.println(n+equipment);
    	System.out.println(n+price);
    	System.out.println(n+note);
    	System.out.println(n+formattedDateTime);*/
        return "showRoomAll";
    } 
    
    @RequestMapping(value = "/RoomUpdate", method = RequestMethod.GET)
    public ModelAndView openFormUpdate(@RequestParam(value="id", required=false, defaultValue="1") Long id) {
       ModelAndView model = new ModelAndView("RoomUpdate2");
       Rooms room=RoomService.getRoomById(Integer.parseInt(id+""));
       List<Branch> branch=BranchService.getAllBranch();
       model.addObject("room",room);
       model.addObject("branch",branch);
       return model;
    }
    
    @RequestMapping(value = "/RoomUpdate", method = RequestMethod.POST)
    public ModelAndView processFormUpdate(@ModelAttribute Rooms room, @RequestParam("BranchName") String branchId) throws SQLException {
       ModelAndView model = new ModelAndView("redirect:/showRoomAll");
       Branch Branch=BranchService.getBranchById(Integer.parseInt(branchId));
       room.setBranch(Branch);
   	
       LocalDateTime now = LocalDateTime.now();   	
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
       String formattedDateTime = now.format(formatter);
   	   Timestamp ts = Timestamp.valueOf(formattedDateTime);
   	   room.setCreatedAt(ts);   
   	RoomService.saveRoom(room);
       System.out.println("aaa"+branchId);
       return model;
    }
    
    @RequestMapping(value = "/RoomDelete", method = RequestMethod.GET)
    public ModelAndView deleteFormUpdate(@RequestParam(value="id", required=false, defaultValue="1") Long id) {
       ModelAndView model = new ModelAndView("showRoomAll");
       Rooms room=RoomService.getRoomById(Integer.parseInt(id+""));
       
       LocalDateTime now = LocalDateTime.now();   	
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
       String formattedDateTime = now.format(formatter);
   	   Timestamp ts = Timestamp.valueOf(formattedDateTime);
   	   room.setDeletedAt(ts); 
   	   RoomService.saveRoom(room);
   	   List<Rooms> roomall=RoomService.getAllRoom();
   	   model.addObject("listRoom",roomall);
       return model;
    }
    
    
    
    
    
 /*   @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        // save employee to database
        employeeService.saveEmployee(employee);
        return "redirect:/";
    } */

 /*   @GetMapping("/Book_a_Room/{id}")
    public String showTaipeiRoom(@PathVariable(value = "id") long id, Model model) {

        // get employee from the service
        Room room = RoomService.getRoomById(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("checkin", room);
        return "update_employee";
    } */
    

   /* @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") long id) {

        // call delete employee method 
        this.employeeService.deleteEmployeeById(id);
        return "redirect:/";
    }*/
}
