package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.PermissionSet;
import com.example.demo.model.Users;
import com.example.demo.model.UserVO;
import com.example.demo.model.Users;
import com.example.demo.repository.PermissionSetRepository;
import com.example.demo.repository.UsersRepository;

@Service
public class UsersServiceImpl implements UsersService {
	
	@Autowired
    private UsersRepository usersRepository;
	
	//新增注入PermissionSetRepository
	@Autowired
	PermissionSetRepository permissionSetRepossitory;
	
	// Create & Update
	@Override
	public void saveUsers(Users users) {
		this.usersRepository.save(users);
		
	}
	
	// Read: All
	@Override
	public List<Users> getUserses() {
		return usersRepository.findAll();
	}
	
	@Override
	public List <Users> getAllUsers() {
		return usersRepository.findAll(); // .findAll()為系統內建method，所以不用寫內容
	}

	@Override
	public List <Users> getByCreated_atBetween(String start_at,String end_at) {
		List<Users> user = usersRepository.findByCreatedAtBetween(start_at,end_at);
		
		return user;
	}
	
	// Read: by Id
	@Override
	public Users getUsersById(Integer id) {
		
		Optional <Users> optional = usersRepository.findById(id);
		
		Users users = null;
		
        if (optional.isPresent()) {
        	users = optional.get();
        } else {
            throw new RuntimeException("Users not found for id: " + id);
        }
        return users;
        
	}
	
	// Delete
	@Override
	public void deleteUsersById(Integer id) {
		this.usersRepository.deleteById(id);
		
	}

	/****************yen jen*******************************/
	/***********************************************/

	// 先瀝出權限ID再根據創立時間排序的方法 這裡寫死，是權限id=1
	public List<UserVO> getUserPermissionSetOrderByCcreatedAt() {
		PermissionSet permissionSet = new PermissionSet();
		permissionSet.setId(1);
		List<Users> users = usersRepository.findByPermissionSetOrderByCreatedAtAsc(permissionSet);// 這裡在丟入權限ID1之後應該已進行此類人群排序了

		List<UserVO> list = new ArrayList<>();

		for (Users u : users) {
			UserVO vo = new UserVO();

			vo.setEmail(u.getEmail());
			vo.setAddress(u.getAddress());
			vo.setAge(u.getAge());
			vo.setCellphone(u.getCellphone());
			vo.setChinesName(u.getChinesName());
			vo.setCreatedAt(u.getCreatedAt());
			vo.setDeletedAt(u.getDeletedAt());
			vo.setEnglishName(u.getEnglishName());
			vo.setPassword(u.getPassword());
			vo.setPermission_id(u.getPermissionSet().getId());
			vo.setGender(u.getGender());
			vo.setUpdatedAt(u.getUpdatedAt());
			vo.setId(u.getId());

			list.add(vo);
		}

		return list;
	}

	// 瀝出權限ID後根據創建日期進行所屬人群排序 權限ID:3
	@Override
	public List<UserVO> getUserPermissionSetOrderByCcreatedAt3() {

		PermissionSet permissionSet = new PermissionSet();
		permissionSet.setId(3);
		List<Users> users = usersRepository.findByPermissionSetOrderByCreatedAtAsc(permissionSet);// 這裡在丟入權限ID3之後應該已進行此類人群排序了

		List<UserVO> list = new ArrayList<>();

		for (Users u : users) {
			UserVO vo = new UserVO();

			vo.setEmail(u.getEmail());
			vo.setAddress(u.getAddress());
			vo.setAge(u.getAge());
			vo.setCellphone(u.getCellphone());
			vo.setChinesName(u.getChinesName());
			vo.setCreatedAt(u.getCreatedAt());
			vo.setDeletedAt(u.getDeletedAt());
			vo.setEnglishName(u.getEnglishName());
			vo.setPassword(u.getPassword());
			vo.setPermission_id(u.getPermissionSet().getId());
			vo.setGender(u.getGender());
			vo.setUpdatedAt(u.getUpdatedAt());
			vo.setId(u.getId());

			list.add(vo);
		}

		return list;
	}

	// 瀝出權限ID後根據創建日期進行所屬人群排序 權限ID:2
	@Override
	public List<UserVO> getUserPermissionSetOrderByCcreatedAt2() {

		PermissionSet permissionSet = new PermissionSet();
		permissionSet.setId(2);
		List<Users> users = usersRepository.findByPermissionSetOrderByCreatedAtAsc(permissionSet);// 這裡在丟入權限ID2之後應該已進行此類人群排序了

		List<UserVO> list = new ArrayList<>();

		for (Users u : users) {
			UserVO vo = new UserVO();

			vo.setEmail(u.getEmail());
			vo.setAddress(u.getAddress());
			vo.setAge(u.getAge());
			vo.setCellphone(u.getCellphone());
			vo.setChinesName(u.getChinesName());
			vo.setCreatedAt(u.getCreatedAt());
			vo.setDeletedAt(u.getDeletedAt());
			vo.setEnglishName(u.getEnglishName());
			vo.setPassword(u.getPassword());
			vo.setPermission_id(u.getPermissionSet().getId());
			vo.setGender(u.getGender());
			vo.setUpdatedAt(u.getUpdatedAt());
			vo.setId(u.getId());

			list.add(vo);
		}

		return list;
	}

	// 根據權限ID排序所有使用者
	@Override
	public List<UserVO> getAllUsersOrderByPermissionSetId() {
		List<Users> list = usersRepository.findAllByOrderByPermissionSet_IdAsc();
		List<UserVO> out = new ArrayList<>();

		for (Users o : list) { // 從list瀝遍出來的o的各項屬性塞入到UserVO的List內
			UserVO vo = new UserVO();
			vo.setEmail(o.getEmail());
			vo.setAddress(o.getAddress());
			vo.setAge(o.getAge());
			vo.setCellphone(o.getCellphone());
			vo.setChinesName(o.getChinesName());
			vo.setCreatedAt(o.getCreatedAt());
			vo.setDeletedAt(o.getDeletedAt());
			vo.setEnglishName(o.getEnglishName());
			vo.setPassword(o.getPassword());
			vo.setPermission_id(o.getPermissionSet().getId());
			vo.setGender(o.getGender());
			vo.setUpdatedAt(o.getUpdatedAt());
			vo.setId(o.getId());

			out.add(vo);
		}

		return out;

	}

	// 更新權限等級
	@Override
	public UserVO updatePermissionSet(String email, Integer id) {
		Users user = usersRepository.findByEmail(email);// 用email找出對應user

		PermissionSet ps = user.getPermissionSet();// 稱之為PermissionSet的id為1的ps

		// 想辦法先拿到權限等級2的PermissionSet，然後將那個2級PermissionSet丟給原本是1級的User
		PermissionSet psUpLv = permissionSetRepossitory.findById(id).get();// 這就是2級權限的..但有纏繞
			 
		user.setPermissionSet(psUpLv);// 重新定義user的PermissionSet
		System.out.println(user.getPermissionSet().getId());

		usersRepository.saveAndFlush(user);// SQL更新

		UserVO vo = new UserVO();

		vo.setEmail(user.getEmail());
		vo.setAddress(user.getAddress());
		vo.setAge(user.getAge());
		vo.setCellphone(user.getCellphone());
		vo.setChinesName(user.getChinesName());
		vo.setEnglishName(user.getEnglishName());
		vo.setGender(user.getGender());
		vo.setId(user.getId());
		vo.setPassword(user.getPassword());
		vo.setPermission_id(user.getPermissionSet().getId());
		vo.setUpdatedAt(user.getUpdatedAt());

		return vo;
	}

	// 根據權限id，找出一群對應此id的List<User>
	@Override
	public List<UserVO> findThePermissionSetUsers(Integer id) {
		PermissionSet permissionSet = permissionSetRepossitory.findById(id).get();// 輸入id，找到對應的那個權限的id

		List<UserVO> vo = this.findAllUser();
		List<UserVO> out = new ArrayList<>();

		for (int i = 0; i < vo.size(); i++) {
			if (vo.get(i).getPermission_id() == permissionSet.getId()) {
				out.add(vo.get(i));
			}
		}

		return out;
	}

	// 查詢所有
	@Override
	public List<UserVO> findAllUser() {
		List<Users> list = usersRepository.findAll();
		List<UserVO> out = new ArrayList<>();

		for (Users o : list) { // 從list瀝遍出來的o的各項屬性塞入到UserVO的List內
			UserVO vo = new UserVO();
			vo.setEmail(o.getEmail());
			vo.setAddress(o.getAddress());
			vo.setAge(o.getAge());
			vo.setCellphone(o.getCellphone());
			vo.setChinesName(o.getChinesName());
			vo.setCreatedAt(o.getCreatedAt());
			vo.setDeletedAt(o.getDeletedAt());
			vo.setEnglishName(o.getEnglishName());
			vo.setPassword(o.getPassword());
			vo.setPermission_id(o.getPermissionSet().getId());
			vo.setGender(o.getGender());
			vo.setUpdatedAt(o.getUpdatedAt());
			vo.setId(o.getId());

			out.add(vo);
		}

		return out;
	}

	// 新增使用者
	@Override
	public Users addUser(Users user) {

		PermissionSet permissionSet = permissionSetRepossitory.findById(3).get();// 第一次註冊的人都先給權限ID2(一般人員)
		System.out.println("permissionSetRepossitory.findById(3).get().getId():"+permissionSet.getId());
		System.out.println("permissionSet的id:"+permissionSet.getId());
		
		user.setPermissionSet(permissionSet);
		System.out.println("user現在得到的PermissionSet的id:"+user.getPermissionSet().getId());
		
		
		usersRepository.save(user);
		return user;
	}

	// 更新使用者資訊
	@Override
	public void updateUser(String email, Users user) {

		Users u = usersRepository.findByEmail(email);

		/** 測試 **/
		// System.out.println("User: " + email);
		// System.out.println("Updated user data: " + user);

		if (u == null) {
			throw new IllegalArgumentException("User with email " + email + " not found");

		}

		if (user.getAddress() != null && user.getAddress().trim().length() > 0) {
			u.setAddress(user.getAddress());
		}
		u.setEmail(user.getEmail());

		if (user.getAge() != null && user.getAge() != 0) {
			u.setAge(user.getAge());
		}

		if (user.getCellphone() != null && user.getCellphone().trim().length() > 0) {
			u.setCellphone(user.getCellphone());
		}

		if (user.getChinesName() != null && user.getChinesName().trim().length() > 0) {
			u.setChinesName(user.getChinesName());
		}

		if (user.getEnglishName() != null && user.getEnglishName().trim().length() > 0) {
			u.setEnglishName(user.getEnglishName());
		}

		if (user.getPassword() != null && user.getPassword().trim().length() > 0) {
			u.setPassword(user.getPassword());
		}

		u.setGender(user.getGender());

		if (user.getSalt() != null && user.getSalt().trim().length() > 0) {
			u.setSalt(user.getSalt());
		}
		
		if (user.getUpdatedAt() != null) {
			u.setUpdatedAt(user.getUpdatedAt());
		}
		
		

		usersRepository.save(u);
	}



	// 軟刪除使用者
	@Override
	public String freezeUser(Integer id) {
		try {
			Users user = usersRepository.findById(id).get();
			
			System.out.println(user.getId());
			user.setDeletedAt(user.getCreatedAt());
			usersRepository.saveAndFlush(user);
			return "已執行軟刪";
		} catch (

		Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	// 找鹽
	@Override
	public String getSaltByEmail(String email) {
		String salt = usersRepository.findSaltByEmail(email);
		return salt;
	}

	// 找出哈希
	@Override
	public String getHashPasswordByEmail(String email) {
		String hashPassword = usersRepository.findPasswordByEmail(email);
		return hashPassword;
	}

	// 用email找出User
		@Override
		public Users getUserByEmail(String email) {
			Users user = new Users();
			user = usersRepository.findByEmail(email);
			return user;
		}

}
