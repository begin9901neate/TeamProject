package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.PermissionSet;
import com.example.demo.model.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {
	
	/******************* 仁輔 ****************************/
	@Query(value = "select * from users where created_at >= ?1 and created_at<=?2", nativeQuery = true)
	List<Users> findByCreatedAtBetween(String start_at,String end_at);
	
	/*******************yen jen****************************/
	//自訂一個透過email來查詢使用者的方法
	@Query("SELECT u FROM Users u WHERE u.email = :email")
    Users findByEmail(@Param("email") String email);
	
	
	//根據命名法則自訂根據PermissionSet_Id排序的方法
	List<Users> findAllByOrderByPermissionSet_IdAsc();
	
	//根據命名法則自訂先瀝出權限ID再根據創立時間排序的方法
	List<Users> findByPermissionSetOrderByCreatedAtAsc(PermissionSet permissionSet);
	
	
	
	//找鹽
	@Query("SELECT u.salt FROM Users u WHERE u.email = ?1")
	String findSaltByEmail(String email);
	
	//找哈希後的密碼
	@Query("SELECT u.password FROM Users u WHERE u.email = ?1")
	String findPasswordByEmail(String email);
}
