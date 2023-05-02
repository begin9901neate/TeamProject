package com.example.demo.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.PermissionSet;
import com.example.demo.model.UserVO;
import com.example.demo.model.Users;
import com.example.demo.repository.UsersRepository;
import com.example.demo.service.PermissionSetService;
import com.example.demo.service.UsersService;

@RestController
public class UsersController {
	
	@Autowired
	private PermissionSetService permissionSetService;
	
	@Autowired
	private UsersService usersService;
	
	//新增注入
	@Autowired
	UsersRepository usersRepository;
	
	
	@GetMapping({"/findUsers"})
	public ModelAndView findUsers() {

		ModelAndView model = new ModelAndView("ianTest/findUsers");
		
		Iterable<PermissionSet> permissionSets = permissionSetService.getPermissionSets();
		model.addObject("permissionSets", permissionSets);
		
		PermissionSet permissionSet = permissionSets.iterator().next();
		model.addObject("permissionSet", permissionSet);
		
		Iterable<Users> userses = usersService.getUserses();
		model.addObject("userses", userses);
		
		return model;
	}
	
	@PostMapping({"/findUsersByPermission"})
	public ModelAndView findUsersByPermission(
		@RequestParam(value = "id", required = false, defaultValue = "1") Integer id) {
		
		ModelAndView model = new ModelAndView("ianTest/findUsers");
		
		Iterable<PermissionSet> permissionSets = permissionSetService.getPermissionSets();
		model.addObject("permissionSets", permissionSets);
		
		PermissionSet permissionSet = permissionSetService.getPermissionSetById(id);
		
		model.addObject("permissionSet", permissionSet);
		model.addObject("userses", permissionSet.getUserses());
		
		return model;
	}
	
	
	/***********************yen jan*********************************/
	/**登入*/
	// 拿到帳密、轉傳登入驗證 /*session測試*/
		@PostMapping(value = "/Pet")
		public ResponseEntity login(@RequestParam("email") String email, @RequestParam("password") String password,
				HttpServletRequest request) {

			List<UserVO> users = usersService.findAllUser();

			for (UserVO u : users) {
				if (email.equals(u.getEmail())) {

					BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

					String storedHashPassword = usersService.getHashPasswordByEmail(email);
					
					System.out.println("使用者輸入的密碼:"+password);
					System.out.println("登入時從SQL內抓出的哈希密碼:"+storedHashPassword);
					
					if (encoder.matches(password, storedHashPassword)) {
						
						HttpSession session = request.getSession();// 取得session物件
						session.setAttribute("userId", u);// 將u存入session內

						UserVO user = (UserVO) session.getAttribute("userId");
						System.out.println(user.getEmail());/**/

						HashMap<String, UserVO> data = new HashMap<String, UserVO>();
						data.put("user", user);

						return ResponseEntity.ok().body(data);
					}

				}
			}

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

		}
		
		
		/**************************************
		 * 頁面ok
		 *********************************************************/
		// 權限更新
		@GetMapping("/updatePs")
		public ModelAndView updatePs() {
			
			return new ModelAndView("newUpdatePermissionSet"); 
		}

		// 用現行的findByEmail找到User，將User的getPermissionSet()拿到對應的PermissionSet，在set id
		@PostMapping("/updatePermissionSet")
		public ResponseEntity updatePermissionSetLevel(@RequestParam("email") String email, @RequestParam("id") Integer id,
				HttpServletRequest request) {
			
			UserVO vo = usersService.updatePermissionSet(email, id);// 後面的id是所希望的更新後權限等級
			
			
			//@Timestamp失效 先自己指定時間
			LocalDateTime nowDate=LocalDateTime.now();
			Timestamp timestamp = Timestamp.valueOf(nowDate);
			
			vo.setUpdatedAt(timestamp);
			
	
			//測試 ok
			Users u = usersRepository.findByEmail(email);
			u.setUpdatedAt(vo.getUpdatedAt());
			usersService.updateUser(email, u);
			usersRepository.save(u);
			
			
		
			HttpSession session = request.getSession();
			session.setAttribute("user", vo);
			UserVO userVo = (UserVO) session.getAttribute("user");
			
		
			HashMap<String, UserVO> data = new HashMap<String, UserVO>();
			data.put("userVo", userVo);

			return ResponseEntity.ok().body(data);

		}

		/*********************************
		 * 頁面ok
		 *******************************************************/
		// 查詢所有使用者
		@GetMapping(value = "/users")
		public ModelAndView findAllUser() {

			List<UserVO> users = usersService.findAllUser();
			ModelAndView model = new ModelAndView("findAllUser");
			model.addObject("users", users);
			return model;
		}

		//查所有使用者、回傳帶進body
		@GetMapping("/userAll")
		public ResponseEntity userAll() {
			List<UserVO> users = usersService.findAllUser();
			 return ResponseEntity.ok().body(users);
		}
		
		
		/*********************************
		 * 頁面ok
		 ******************************************************/
		// 註冊頁
		@GetMapping("/Register")
		public ModelAndView register() {

			return new ModelAndView("signup");// 傳這個view
		}

		// 新增使用者
		@PostMapping("/add")
		public String addUser(@RequestBody Users user) {

			// 先檢驗是否有重複帳號
			String JsonMessage = null;
			List<UserVO> list = usersService.findAllUser();

			for (UserVO vo : list) {
				if (vo.getEmail().equals(user.getEmail())) {
					JsonMessage = "AddError";
					return JsonMessage;
				}
			}

			// 加密 開始測試 產鹽
			String salt = BCrypt.gensalt();
			
			System.out.println("註冊時的鹽值:"+salt);
			
			// BCrypt演算法進行哈希運算
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String hashedPassword = encoder.encode(user.getPassword());

			System.out.println("註冊時輸入的密碼:"+user.getPassword());
			System.out.println("註冊時的哈希密碼:"+hashedPassword);
			
			// 將原本使用者輸入的密碼改為哈希後的密碼
			user.setPassword(hashedPassword);
			user.setSalt(salt);
			
			
			//@Timestamp 失效，現在先自己指定當前時間
			LocalDateTime nowDate=LocalDateTime.now();
			Timestamp timestamp = Timestamp.valueOf(nowDate);
			
			user.setCreatedAt(timestamp);

			usersService.addUser(user);
			// 帳號沒重複，成功註冊
			System.out.println("註冊成功時的哈希密碼:"+user.getPassword());
			System.out.println("註冊成功時的鹽值:"+user.getSalt());
			JsonMessage = "AddSuccess";
			return JsonMessage;

		}

		/****************************
		 * 頁面ok
		 ************************************************************/
		// 更新個人資料頁面
		@GetMapping("/Update")
		public ModelAndView update() {

			return new ModelAndView("update");// 傳這個view
		}

		// 修改使用者資料
		@PutMapping("/update/{email}")
		public String updateUser(@RequestBody Users user, @PathVariable("email") String email) {

			//@Timestamp失效 先自己指定時間
			LocalDateTime nowDate=LocalDateTime.now();
			Timestamp timestamp = Timestamp.valueOf(nowDate);
			// 測試
			user.setUpdatedAt(timestamp);
			//
			
			try {
				usersService.updateUser(email, user);
				
			
				return "update is success";
			} catch (Exception e) {
				e.getMessage();
			}
			return email + " is not exist";

		}
		
		
		//更新密碼
		@GetMapping("/Update/password")
		public ModelAndView updatePassword() {

			return new ModelAndView("updatePassword");// 傳這個view
		}
		
		@PutMapping("/update/password/{email}")
		public String updatePassword(@RequestBody Map<String,String> data/*String newPassword*/, @PathVariable("email") String email) {

			String newPassword=data.get("password");//因為前端傳來JSON格式物件，我只想拿出password使用
			
			List<UserVO> list=usersService.findAllUser();
			
			for(UserVO u:list) {
				if(u.getEmail().equals(email)) {
					
					String salt = BCrypt.gensalt();
					
					System.out.println("更改密碼時宣告的鹽值:"+salt);
					
					BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
					String hashedPassword = encoder.encode(newPassword);//新密碼進行哈希運算
					
					System.out.println("使用者輸入的新密碼:"+newPassword);
					System.out.println("哈希後的新密碼:"+hashedPassword);
					
					u.setPassword(hashedPassword);//將舊密碼設置為新哈希密碼
					u.setSalt(salt);
					
					Users user=new Users();
					user.setEmail(u.getEmail());
					user.setPassword(u.getPassword());
					user.setSalt(u.getSalt());
					
					System.out.println("要更新的使用者的email:"+user.getEmail());
					System.out.println("此使用者的新密碼:"+user.getPassword());
					System.out.println("此使用者的新鹽值"+user.getSalt());
					
					try {
						usersService.updateUser(email, user);
						
						
						System.out.println("更新完畢後的密碼:"+u.getPassword());
						System.out.println("更新完畢後的密碼:"+user.getPassword());
						System.out.println("更新完畢後的鹽值:"+u.getSalt());
						System.out.println("更新完畢後的鹽值:"+user.getSalt());
						
						return "update is success";
					} catch (Exception e) {
						e.getMessage();
					}
				}
			}
			
			
			return email + " is not exist";

		}


		/************最後採用方案**************************/
		// 軟刪除使用者
		@GetMapping("/delete/by2/{id}")
		public ModelAndView deleteUser(@PathVariable("id") Integer id) {
			usersService.freezeUser(id);
			return new ModelAndView("deleteSuccess");
		}
		
		
		/**********************************************************************************************/

		// 丟一個url，讓他能傳送UserDetails這個html出來
		@GetMapping("/UserInterfacePage")
		public ModelAndView UserInterfacePage() {
			return new ModelAndView("UserDetails");
		}

		// 丟一個url，讓他能傳送Admin這個html出來
		@GetMapping("/AdminManagerInterface")
		public ModelAndView AdminManagerInterface() {
			return new ModelAndView("Admin");
		}


		/**********************************************************************************************/
		// 區分權限人:

		// A段:
		// 瀝出權限後根據創立時間排序該權限2的所有人群:這群人是員工
		@GetMapping("/orderbyCreateAt2")
		public ModelAndView orderByCreateAtUsersForPs2() {
			List<UserVO> vo = usersService.getUserPermissionSetOrderByCcreatedAt2();

			ModelAndView model = new ModelAndView("orderbyCreateAt");
			model.addObject("users", vo);
			return model;
		}

		// B段:
		// 瀝出權限後根據創立時間排序該權限3的所有人群:這群人是顧客
		@GetMapping("/orderbyCreateAt3")
		public ModelAndView orderByCreateAtUsersForPs3() {
			List<UserVO> vo = usersService.getUserPermissionSetOrderByCcreatedAt3();

			ModelAndView model = new ModelAndView("orderbyCreateAt3");
			model.addObject("users", vo);
			return model;
		}

		// 備用段: Admin
		@GetMapping("/orderbyCreateAt1")
		public ModelAndView orderByCreateAtUsersForPs() {
			List<UserVO> vo = usersService.getUserPermissionSetOrderByCcreatedAt();

			ModelAndView model = new ModelAndView("orderbyCreateAtAdmin");
			model.addObject("users", vo);
			return model;
		}

		/**************************************************************************************************/

		// 查詢個人資料按鈕、跳轉OneUser頁面、等待輸入資訊
		@GetMapping("/findUserOriginal")
		public ModelAndView findUserOriginal() {
			return new ModelAndView("OneUserOriginal");// 傳這個view
		}

		// 輸入帳密資訊後來到這裡，見到個人資料
		@PostMapping(value = "/userOriginal")
		public ModelAndView getUserOriginal(@RequestBody Users user) {

			List<UserVO> users = usersService.findAllUser();
			for (UserVO u : users) {
				if ((user.getEmail()).equals(u.getEmail())) {

					BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

					String storedHashPassword = usersService.getHashPasswordByEmail(user.getEmail());
					if (encoder.matches(user.getPassword(), storedHashPassword)) {

						ModelAndView model = new ModelAndView("userViewO");
						model.addObject("user", u);

						return model;
					}

				}
			}
			ModelAndView model = new ModelAndView("notUserViewO");
			return model;
		}

		/***************************
		 * Admin查詢個人資料******頁面ok
		 ******************************************************/

		// 輸入帳密資訊後來到這裡，見到個人資料
		@GetMapping(value = "/user/{email}")
		public ModelAndView getUser(@PathVariable("email") String email) {

			List<UserVO> users = usersService.findAllUser();
			for (UserVO u : users) {

				if ((email).equals(u.getEmail())) {

					ModelAndView model = new ModelAndView("userView");
					model.addObject("user", u);

					return model;

				}
			}
			ModelAndView model = new ModelAndView("notUserView");
			return model;
		}
	

		
		/***********Admin註冊*********************/
		@GetMapping("/AdminSignUp")
		public ModelAndView AdminSignUp() {

			return new ModelAndView("AdminSignUp");// 傳這個view
		}
		
		
		
		
}
