package com.example.demo.service;

import java.util.List;


import com.example.demo.model.UserVO;
import com.example.demo.model.Users;


public interface UsersService {
	
    // Create & Update
    void saveUsers(Users users);
    
    // Read: All
    List<Users> getUserses();
    List <Users> getAllUsers();			//取得所有使用者資料
	List <Users> getByCreated_atBetween(String created_at,String created_at2);		//取得近期新增使用者
    
    
    // Read: by Id
    Users getUsersById(Integer id);
    
    // Delete
    void deleteUsersById(Integer id);

    /********yen jen***************/
  //先瀝出權限ID再根據創立時間排序的方法 這裡寫死，是權限id=3
  	List<UserVO> getUserPermissionSetOrderByCcreatedAt();
  	
  	
  	//先瀝出權限ID再根據創立時間排序的方法 這裡寫死，是權限id=3
  	List<UserVO> getUserPermissionSetOrderByCcreatedAt3();
  	
  	
  	//先瀝出權限ID再根據創立時間排序的方法 這裡寫死，是權限id=2
  	List<UserVO> getUserPermissionSetOrderByCcreatedAt2();
  	
  	//根據權限Id排序所有使用者
  	List<UserVO> getAllUsersOrderByPermissionSetId();
  	
  	
  	//找user，利用email
  	Users getUserByEmail(String email);
  	
  	//UpdatePermissionSet 用email找出使用者、並利用id更新權限等級
  	UserVO updatePermissionSet(String email,Integer id);	
  	
  	//查找根據權限id查出的使用者群
  	List<UserVO> findThePermissionSetUsers(Integer id);
  	
  	
  	//查找所有使用者
  	List<UserVO> findAllUser();
  	
  	//新增使用者
  	Users addUser(Users user);
  	
  	//修改使用者資訊
  	void updateUser(String email, Users user);
  	

  	//軟刪除使用者 
  	String freezeUser(Integer id);
  	
  	//找出鹽
  	String getSaltByEmail(String email);
  	
  	//找出哈希
  	String getHashPasswordByEmail(String email);
}
