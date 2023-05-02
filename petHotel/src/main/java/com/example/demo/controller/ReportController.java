package com.example.demo.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*; 


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Users;
import com.example.demo.model.OrderRooms;
import com.example.demo.model.Rooms;
import com.example.demo.model.RoomSession;
import com.example.demo.model.OrderCommodity;
import com.example.demo.service.UsersService;
import com.example.demo.service.OrderRoomsService;
import com.example.demo.service.OrderCommodityService;
import com.example.demo.service.RoomSessionService;
import com.example.demo.service.RoomsService;



@Controller
@RequestMapping(value = "/adminReport")
public class ReportController {
	
	@Autowired		// 由系統產生物件
    private UsersService UserService;
	@Autowired		
    private OrderRoomsService OrderRoomService;
	@Autowired		
    private RoomsService RoomService;
	@Autowired		
    private OrderCommodityService OrderCommodityService;
	@Autowired		
    private RoomSessionService RoomSessionService;
	
    
    @GetMapping(value={"/"})
    public String viewReportPage(Model model) {
        //model.addAttribute("listUsers", UserService.getAllUsers());
    	//model.addAttribute("listUsers", UsersRepository.findAll());
        //return new ModelAndView("adminReport");
        return "adminReport";
    }
    
    @Description("user資料下载")
    @RequestMapping(value = "/downloadAllUsers", method = RequestMethod.GET)
    public String downloadALLUsers(HttpServletRequest request, HttpServletResponse response) {
    	 try {
    		 
         	//數據庫獲取導出的數據
             List<Users> Users = UserService.getAllUsers();
             //創建excel文檔
             HSSFWorkbook workbook = new HSSFWorkbook();
             //創建一個excel表單
             HSSFSheet sheet = workbook.createSheet();
             //在表單中添加表頭
             HSSFRow row = sheet.createRow(0);
             //設置第一行表頭內容
             String[] headers = {"email", "chines_name", " english_name", "cellphone", "address", "age", "created_at"};
             //創建表頭
             for (int i = 0; i < headers.length; i++) {
                 HSSFCell cell = row.createCell(i);
                 HSSFRichTextString textString = new HSSFRichTextString(headers[i]);
                 cell.setCellValue(textString);
             }
             //從第二行開始循環輸出
             int rowNum = 1;
             for (Users User : Users) {
                 HSSFRow sheetRow = sheet.createRow(rowNum);
                 sheetRow.createCell(0).setCellValue(User.getEmail());
                 sheetRow.createCell(1).setCellValue(User.getChinesName());
                 sheetRow.createCell(2).setCellValue(User.getEnglishName());
                 sheetRow.createCell(3).setCellValue(User.getCellphone());
                 sheetRow.createCell(4).setCellValue(User.getAddress());
                 sheetRow.createCell(5).setCellValue(User.getAge());
                 sheetRow.createCell(6).setCellValue(User.getCreatedAt());
                 //自動設置列寬
                 sheet.autoSizeColumn(rowNum - 1);
                 rowNum++;
             }
             //設置導出的excel的文件名
             String fileName = "AllUsers.xls";
             String realPath = ".\\";//这里配置文件路径
             File file = new File(realPath , fileName);
             response.setContentType("application/force-download");// 设置强制下载不打开
             response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
             response.flushBuffer();
             workbook.write(response.getOutputStream());
         } catch (Exception e) {
             e.printStackTrace();
             //log.info("導出失敗");
         }
    	 return null;
    	 //return "redirect:/";
    }
    
    @RequestMapping(value = "/selectTimeUsers", method = RequestMethod.POST)
    public String downloadUser_Time(HttpServletRequest request, HttpServletResponse response) {
    	 try {
    		 //Date start_time=new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("start")); //equest.getParameter("start")的值是string"04/12/2023"
    		 //Date end_time=new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("end"));
    		 String start_time=request.getParameter("start_user");
    		 String end_time=request.getParameter("end_user");
         	//數據庫獲取導出的數據
    		 List<Users> Users = UserService.getByCreated_atBetween(start_time, end_time);
             //創建excel文檔
             HSSFWorkbook workbook = new HSSFWorkbook();
             //創建一個excel表單
             HSSFSheet sheet = workbook.createSheet();
             //在表單中添加表頭
             HSSFRow row = sheet.createRow(0);
             //設置第一行表頭內容
             String[] headers = {"email", "chines_name", " english_name", "cellphone", "address", "age", "created_at"};
             //創建表頭
             for (int i = 0; i < headers.length; i++) {
                 HSSFCell cell = row.createCell(i);
                 HSSFRichTextString textString = new HSSFRichTextString(headers[i]);
                 cell.setCellValue(textString);
             }
             //從第二行開始循環輸出
             int rowNum = 1;
             for (Users User : Users) {
                 HSSFRow sheetRow = sheet.createRow(rowNum);
                 sheetRow.createCell(0).setCellValue(User.getEmail());
                 sheetRow.createCell(1).setCellValue(User.getChinesName());
                 sheetRow.createCell(2).setCellValue(User.getEnglishName());
                 sheetRow.createCell(3).setCellValue(User.getCellphone());
                 sheetRow.createCell(4).setCellValue(User.getAddress());
                 sheetRow.createCell(5).setCellValue(User.getAge());
                 sheetRow.createCell(6).setCellValue(User.getCreatedAt());
                 //自動設置列寬
                 sheet.autoSizeColumn(rowNum - 1);
                 rowNum++;
             }
             //設置導出的excel的文件名
             String fileName = "Select_Time_Users.xls";
             String realPath = ".\\";//这里配置文件路径
             File file = new File(realPath , fileName);
             response.setContentType("application/force-download");// 设置强制下载不打开
             response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
             response.flushBuffer();
             workbook.write(response.getOutputStream());
         } catch (Exception e) {
             e.printStackTrace();
             //log.info("導出失敗");
         }
    	 return null;
    	 //return "redirect:/";
    }
    
    @Description("orderRoom資料下载")
    @RequestMapping(value = "/downloadAllorderRooms", method = RequestMethod.GET)
    public String downloadALLorderRooms(HttpServletRequest request, HttpServletResponse response) {
    	 try {
    		 
         	//數據庫獲取導出的數據
    		 List<OrderRooms> OrderRooms =null;
             OrderRooms = OrderRoomService.getAllOrderRooms();
             
             //創建excel文檔
             HSSFWorkbook workbook = new HSSFWorkbook();
             //創建一個excel表單
             HSSFSheet sheet = workbook.createSheet();
             //在表單中添加表頭
             HSSFRow row = sheet.createRow(0);
             //設置第一行表頭內容
             String[] headers = {"customer_id", "customer_name", "room_id","room_name", "payment_method", "amount", "complete", "created_at", "deleted_at", "deleted_reason"};
             //創建表頭
             for (int i = 0; i < headers.length; i++) {
                 HSSFCell cell = row.createCell(i);
                 HSSFRichTextString textString = new HSSFRichTextString(headers[i]);
                 cell.setCellValue(textString);
             }
             //從第二行開始循環輸出
             int rowNum = 1;
             for (OrderRooms OrderRoom : OrderRooms) {
                 HSSFRow sheetRow = sheet.createRow(rowNum);
                 sheetRow.createCell(0).setCellValue(OrderRoom.getUsers().getId());
                 sheetRow.createCell(1).setCellValue(OrderRoom.getUsers().getChinesName());
                 sheetRow.createCell(2).setCellValue(OrderRoom.getRooms().getId());
                 sheetRow.createCell(3).setCellValue(OrderRoom.getRooms().getName());
                 sheetRow.createCell(4).setCellValue(OrderRoom.getPaymentMethod());
                 sheetRow.createCell(5).setCellValue(OrderRoom.getAmount());
                 sheetRow.createCell(6).setCellValue(OrderRoom.getComplete());
                 sheetRow.createCell(7).setCellValue(OrderRoom.getCreatedAt());
                 if(OrderRoom.getDeletedAt() ==null || OrderRoom.getDeletedAt().equals("")) {		//因為這兩行沒有資料而產生date must not be null的問題
                	 sheetRow.createCell(8).setCellValue("null");
                 }else {
                	 sheetRow.createCell(8).setCellValue(OrderRoom.getDeletedAt());
                 }
                 if(OrderRoom.getDeletedReason() ==null || OrderRoom.getDeletedReason().equals("")) {
                	 sheetRow.createCell(9).setCellValue("null");
                 }else {
                	 sheetRow.createCell(9).setCellValue(OrderRoom.getDeletedReason());
                 }
            	 
                 //自動設置列寬
                 sheet.autoSizeColumn(rowNum - 1);
                 rowNum++;
             }
             //設置導出的excel的文件名
             String fileName = "AllOrderRooms.xls";
             String realPath = ".\\";//这里配置文件路径
             File file = new File(realPath , fileName);
             response.setContentType("application/force-download");// 设置强制下载不打开
             response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
             response.flushBuffer();
             workbook.write(response.getOutputStream());
         } catch (Exception e) {
             e.printStackTrace();
             //log.info("導出失敗");
         }
    	 return null;
    	 //return "redirect:/";
    }
    
    @RequestMapping(value = "/selectTimeOrderRooms", method = RequestMethod.POST)
    public String downloadOrderRoom_Time(HttpServletRequest request, HttpServletResponse response) {
    	 try {
    		 String start_time=request.getParameter("start_orderRoom");
    		 String end_time=request.getParameter("end_orderRoom");
         	//數據庫獲取導出的數據
    		 List<OrderRooms> OrderRooms = OrderRoomService.getByCreated_atBetween(start_time, end_time);
             //創建excel文檔
             HSSFWorkbook workbook = new HSSFWorkbook();
             //創建一個excel表單
             HSSFSheet sheet = workbook.createSheet();
             //在表單中添加表頭
             HSSFRow row = sheet.createRow(0);
             //設置第一行表頭內容
             String[] headers = {"customer_id", "customer_name", "room_id","room_name", "payment_method", "amount", "complete", "created_at", "deleted_at", "deleted_reason"};
             //創建表頭
             for (int i = 0; i < headers.length; i++) {
                 HSSFCell cell = row.createCell(i);
                 HSSFRichTextString textString = new HSSFRichTextString(headers[i]);
                 cell.setCellValue(textString);
             }
             //從第二行開始循環輸出
             int rowNum = 1;
             for (OrderRooms OrderRoom : OrderRooms) {
                 HSSFRow sheetRow = sheet.createRow(rowNum);
                 sheetRow.createCell(0).setCellValue(OrderRoom.getUsers().getId());
                 sheetRow.createCell(1).setCellValue(OrderRoom.getUsers().getChinesName());
                 sheetRow.createCell(2).setCellValue(OrderRoom.getRooms().getId());
                 sheetRow.createCell(3).setCellValue(OrderRoom.getRooms().getName());
                 sheetRow.createCell(4).setCellValue(OrderRoom.getPaymentMethod());
                 sheetRow.createCell(5).setCellValue(OrderRoom.getAmount());
                 sheetRow.createCell(6).setCellValue(OrderRoom.getComplete());
                 sheetRow.createCell(7).setCellValue(OrderRoom.getCreatedAt());
                 if(OrderRoom.getDeletedAt() ==null || OrderRoom.getDeletedAt().equals("")) {		//因為這兩行沒有資料而產生date must not be null的問題
                	 sheetRow.createCell(8).setCellValue("null");
                 }else {
                	 sheetRow.createCell(8).setCellValue(OrderRoom.getDeletedAt());
                 }
                 if(OrderRoom.getDeletedReason() ==null || OrderRoom.getDeletedReason().equals("")) {
                	 sheetRow.createCell(9).setCellValue("null");
                 }else {
                	 sheetRow.createCell(9).setCellValue(OrderRoom.getDeletedReason());
                 }
                 //自動設置列寬
                 sheet.autoSizeColumn(rowNum - 1);
                 rowNum++;
             }
             //設置導出的excel的文件名
             String fileName = "Select_Time_OrderRooms.xls";
             String realPath = ".\\";//这里配置文件路径
             File file = new File(realPath , fileName);
             response.setContentType("application/force-download");// 设置强制下载不打开
             response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
             response.flushBuffer();
             workbook.write(response.getOutputStream());
         } catch (Exception e) {
             e.printStackTrace();
             //log.info("導出失敗");
         }
    	 return null;
    	 //return "redirect:/";
    }
    
    @Description("RoomSession資料下载")
    @RequestMapping(value = "/downloadAllRoomSessions", method = RequestMethod.GET)
    public String downloadALLRoomSessions(HttpServletRequest request, HttpServletResponse response) {
    	 try {
    		 
         	//數據庫獲取導出的數據
    		  List<RoomSession> RoomSessions = RoomSessionService.getAllRoomSessions();

             //創建excel文檔
             HSSFWorkbook workbook = new HSSFWorkbook();
             //創建一個excel表單
             HSSFSheet sheet = workbook.createSheet();
             //在表單中添加表頭
             HSSFRow row = sheet.createRow(0);
             //設置第一行表頭內容
             String[] headers = {"customer_id", "customer_name", "order_room_id","room_name", "reservation_time","attend_type", "checkin_time", "pet_num", "start_datetime", "note", "created_at"};
             //創建表頭
             for (int i = 0; i < headers.length; i++) {
                 HSSFCell cell = row.createCell(i);
                 HSSFRichTextString textString = new HSSFRichTextString(headers[i]);
                 cell.setCellValue(textString);
             }
             //從第二行開始循環輸出
             int rowNum = 1;
             for (RoomSession RoomSession : RoomSessions) {
                 HSSFRow sheetRow = sheet.createRow(rowNum);
                 sheetRow.createCell(0).setCellValue(RoomSession.getUsers().getId());
                 sheetRow.createCell(1).setCellValue(RoomSession.getUsers().getChinesName());
                 sheetRow.createCell(2).setCellValue(RoomSession.getOrderRooms().getId());
                 sheetRow.createCell(3).setCellValue(RoomSession.getRooms().getName());
                 sheetRow.createCell(4).setCellValue(RoomSession.getReservationTime());
                 sheetRow.createCell(5).setCellValue(RoomSession.getAttendType());
                 if(RoomSession.getCheckinTime() ==null || RoomSession.getCheckinTime().equals("")) {		//因為這行沒有資料而產生date must not be null的問題
                	 sheetRow.createCell(6).setCellValue("null");
                 }else {
                	 sheetRow.createCell(6).setCellValue(RoomSession.getCheckinTime());
                 }
                 if(RoomSession.getPetNum() ==null || RoomSession.getPetNum().equals("")) {		
                	 sheetRow.createCell(7).setCellValue("null");
                 }else {
                	 sheetRow.createCell(7).setCellValue(RoomSession.getPetNum());
                 }
                 if(RoomSession.getStartDatetime() ==null || RoomSession.getStartDatetime().equals("")) {		
                	 sheetRow.createCell(8).setCellValue("null");
                 }else {
                	 sheetRow.createCell(8).setCellValue(RoomSession.getStartDatetime());
                 }
                 if(RoomSession.getNote() ==null || RoomSession.getNote().equals("")) {
                	 sheetRow.createCell(9).setCellValue("null");
                 }else {
                	 sheetRow.createCell(9).setCellValue(RoomSession.getNote());
                 }
                 sheetRow.createCell(10).setCellValue(RoomSession.getCreatedAt());

                 //自動設置列寬
                 sheet.autoSizeColumn(rowNum - 1);
                 rowNum++;
             }
             //設置導出的excel的文件名
             String fileName = "AllRoomSessions.xls";
             String realPath = ".\\";//这里配置文件路径
             File file = new File(realPath , fileName);
             response.setContentType("application/force-download");// 设置强制下载不打开
             response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
             response.flushBuffer();
             workbook.write(response.getOutputStream());
         } catch (Exception e) {
             e.printStackTrace();
             //log.info("導出失敗");
         }
    	 return null;
    	 //return "redirect:/";
    }
    
    @RequestMapping(value = "/selectTimeRoomSessions", method = RequestMethod.POST)
    public String downloadRoomSession_Time(HttpServletRequest request, HttpServletResponse response) {
    	 try {
    		 String start_time=request.getParameter("start_RoomSession");
    		 String end_time=request.getParameter("end_RoomSession");
         	//數據庫獲取導出的數據
    		 List<RoomSession> RoomSessions = RoomSessionService.getByReservationTimeBetween(start_time, end_time);
             //創建excel文檔
             HSSFWorkbook workbook = new HSSFWorkbook();
             //創建一個excel表單
             HSSFSheet sheet = workbook.createSheet();
             //在表單中添加表頭
             HSSFRow row = sheet.createRow(0);
             //設置第一行表頭內容
             String[] headers = {"customer_id", "customer_name", "order_room_id","room_name", "reservation_time","attend_type", "checkin_time", "pet_num", "start_datetime", "note", "created_at"};
             //創建表頭
             for (int i = 0; i < headers.length; i++) {
                 HSSFCell cell = row.createCell(i);
                 HSSFRichTextString textString = new HSSFRichTextString(headers[i]);
                 cell.setCellValue(textString);
             }
             //從第二行開始循環輸出
             int rowNum = 1;
             for (RoomSession RoomSession : RoomSessions) {
                 HSSFRow sheetRow = sheet.createRow(rowNum);
                 sheetRow.createCell(0).setCellValue(RoomSession.getUsers().getId());
                 sheetRow.createCell(1).setCellValue(RoomSession.getUsers().getChinesName());
                 sheetRow.createCell(2).setCellValue(RoomSession.getOrderRooms().getId());
                 sheetRow.createCell(3).setCellValue(RoomSession.getRooms().getName());
                 sheetRow.createCell(4).setCellValue(RoomSession.getReservationTime());
                 sheetRow.createCell(5).setCellValue(RoomSession.getAttendType());
                 if(RoomSession.getCheckinTime() ==null || RoomSession.getCheckinTime().equals("")) {		//因為這行沒有資料而產生date must not be null的問題
                	 sheetRow.createCell(6).setCellValue("null");
                 }else {
                	 sheetRow.createCell(6).setCellValue(RoomSession.getCheckinTime());
                 }
                 if(RoomSession.getPetNum() ==null || RoomSession.getPetNum().equals("")) {		
                	 sheetRow.createCell(7).setCellValue("null");
                 }else {
                	 sheetRow.createCell(7).setCellValue(RoomSession.getPetNum());
                 }
                 if(RoomSession.getStartDatetime() ==null || RoomSession.getStartDatetime().equals("")) {		
                	 sheetRow.createCell(8).setCellValue("null");
                 }else {
                	 sheetRow.createCell(8).setCellValue(RoomSession.getStartDatetime());
                 }
                 if(RoomSession.getNote() ==null || RoomSession.getNote().equals("")) {
                	 sheetRow.createCell(9).setCellValue("null");
                 }else {
                	 sheetRow.createCell(9).setCellValue(RoomSession.getNote());
                 }
                 sheetRow.createCell(10).setCellValue(RoomSession.getCreatedAt());

                 //自動設置列寬
                 sheet.autoSizeColumn(rowNum - 1);
                 rowNum++;
             }
             //設置導出的excel的文件名
             String fileName = "Select_Time_RoomSessions.xls";
             String realPath = ".\\";//这里配置文件路径
             File file = new File(realPath , fileName);
             response.setContentType("application/force-download");// 设置强制下载不打开
             response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
             response.flushBuffer();
             workbook.write(response.getOutputStream());
         } catch (Exception e) {
             e.printStackTrace();
             //log.info("導出失敗");
         }
    	 return null;
    	 //return "redirect:/";
    }
    
    @Description("orderCommodity資料下载")
    @RequestMapping(value = "/downloadAllorderCommodity", method = RequestMethod.GET)
    public String downloadALLorderCommoditys(HttpServletRequest request, HttpServletResponse response) {
    	 try {
    		 
         	//數據庫獲取導出的數據
    		  List<OrderCommodity> OrderCommoditys = OrderCommodityService.getAllOrderCommoditys();
             
             //創建excel文檔
             HSSFWorkbook workbook = new HSSFWorkbook();
             //創建一個excel表單
             HSSFSheet sheet = workbook.createSheet();
             //在表單中添加表頭
             HSSFRow row = sheet.createRow(0);
             //設置第一行表頭內容
             String[] headers = {"customer_id", "customer_name", "commodity_id","commodity_name", "count", "amount", "payment_method", "complete", "created_at", "deleted_at"};
             //創建表頭
             for (int i = 0; i < headers.length; i++) {
                 HSSFCell cell = row.createCell(i);
                 HSSFRichTextString textString = new HSSFRichTextString(headers[i]);
                 cell.setCellValue(textString);
             }
             //從第二行開始循環輸出
             int rowNum = 1;
             for (OrderCommodity OrderCommodity : OrderCommoditys) {
                 HSSFRow sheetRow = sheet.createRow(rowNum);
                 sheetRow.createCell(0).setCellValue(OrderCommodity.getUsers().getId());
                 sheetRow.createCell(1).setCellValue(OrderCommodity.getUsers().getChinesName());
                 sheetRow.createCell(2).setCellValue(OrderCommodity.getCommodity().getId());
                 sheetRow.createCell(3).setCellValue(OrderCommodity.getCommodity().getName());
                 sheetRow.createCell(4).setCellValue(OrderCommodity.getCount());
                 sheetRow.createCell(5).setCellValue(OrderCommodity.getAmount());
                 sheetRow.createCell(6).setCellValue(OrderCommodity.getPaymentMethod());
                 sheetRow.createCell(7).setCellValue(OrderCommodity.getComplete());
                 sheetRow.createCell(8).setCellValue(OrderCommodity.getCreatedAt());
                 if(OrderCommodity.getDeletedAt() ==null || OrderCommodity.getDeletedAt().equals("")) {		//因為這行沒有資料而產生date must not be null的問題
                	 sheetRow.createCell(9).setCellValue("null");
                 }else {
                	 sheetRow.createCell(9).setCellValue(OrderCommodity.getDeletedAt());
                 }

            	 
                 //自動設置列寬
                 sheet.autoSizeColumn(rowNum - 1);
                 rowNum++;
             }
             //設置導出的excel的文件名
             String fileName = "AllOrderCommoditys.xls";
             String realPath = ".\\";//这里配置文件路径
             File file = new File(realPath , fileName);
             response.setContentType("application/force-download");// 设置强制下载不打开
             response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
             response.flushBuffer();
             workbook.write(response.getOutputStream());
         } catch (Exception e) {
             e.printStackTrace();
             //log.info("導出失敗");
         }
    	 return null;
    	 //return "redirect:/";
    }
    
    @RequestMapping(value = "/selectTimeOrderCommodity", method = RequestMethod.POST)
    public String downloadOrderCommodity_Time(HttpServletRequest request, HttpServletResponse response) {
    	 try {
    		 String start_time=request.getParameter("start_OrderCommodity");
    		 String end_time=request.getParameter("end_OrderCommodity");
         	//數據庫獲取導出的數據
    		 List<OrderCommodity> OrderCommoditys = OrderCommodityService.getByCreated_atBetween(start_time, end_time);
             //創建excel文檔
             HSSFWorkbook workbook = new HSSFWorkbook();
             //創建一個excel表單
             HSSFSheet sheet = workbook.createSheet();
             //在表單中添加表頭
             HSSFRow row = sheet.createRow(0);
             //設置第一行表頭內容
             String[] headers = {"customer_id", "customer_name", "commodity_id","commodity_name", "count", "amount", "payment_method", "complete", "created_at", "deleted_at"};
             //創建表頭
             for (int i = 0; i < headers.length; i++) {
                 HSSFCell cell = row.createCell(i);
                 HSSFRichTextString textString = new HSSFRichTextString(headers[i]);
                 cell.setCellValue(textString);
             }
             //從第二行開始循環輸出
             int rowNum = 1;
             for (OrderCommodity OrderCommodity : OrderCommoditys) {
                 HSSFRow sheetRow = sheet.createRow(rowNum);
                 sheetRow.createCell(0).setCellValue(OrderCommodity.getUsers().getId());
                 sheetRow.createCell(1).setCellValue(OrderCommodity.getUsers().getChinesName());
                 sheetRow.createCell(2).setCellValue(OrderCommodity.getCommodity().getId());
                 sheetRow.createCell(3).setCellValue(OrderCommodity.getCommodity().getName());
                 sheetRow.createCell(4).setCellValue(OrderCommodity.getCount());
                 sheetRow.createCell(5).setCellValue(OrderCommodity.getAmount());
                 sheetRow.createCell(6).setCellValue(OrderCommodity.getPaymentMethod());
                 sheetRow.createCell(7).setCellValue(OrderCommodity.getComplete());
                 sheetRow.createCell(8).setCellValue(OrderCommodity.getCreatedAt());
                 if(OrderCommodity.getDeletedAt() ==null || OrderCommodity.getDeletedAt().equals("")) {		//因為這兩行沒有資料而產生date must not be null的問題
                	 sheetRow.createCell(9).setCellValue("null");
                 }else {
                	 sheetRow.createCell(9).setCellValue(OrderCommodity.getDeletedAt());
                 }
                 //自動設置列寬
                 sheet.autoSizeColumn(rowNum - 1);
                 rowNum++;
             }
             //設置導出的excel的文件名
             String fileName = "Select_Time_OrderCommoditys.xls";
             String realPath = ".\\";//这里配置文件路径
             File file = new File(realPath , fileName);
             response.setContentType("application/force-download");// 设置强制下载不打开
             response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
             response.flushBuffer();
             workbook.write(response.getOutputStream());
         } catch (Exception e) {
             e.printStackTrace();
             //log.info("導出失敗");
         }
    	 return null;
    	 //return "redirect:/";
    }
}
