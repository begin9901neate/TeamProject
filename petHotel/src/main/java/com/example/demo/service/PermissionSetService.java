package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.PermissionSet;

@Service //增加@Service
public interface PermissionSetService {
	
    // Create & Update
    void savePermissionSet(PermissionSet permissionSet);
    
    // Read: All
    List<PermissionSet> getPermissionSets();
    
    // Read: by Id
    PermissionSet getPermissionSetById(Integer id);
    
    // Delete
    void deletePermissionSetById(Integer id);

    
    /***********yen jen********/
  //找到權限id
  	PermissionSet findById(Integer id);
}
