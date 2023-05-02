package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Branch;


public interface BranchService {
	
    // Create & Update
    void saveBranch(Branch branch);
    
    // Read: All
    List<Branch> getBranches();
    List <Branch> getAllBranch();
    
    // Read: by Id
    Branch getBranchById(Integer id);
    
    // Delete
    void deleteBranchById(Integer id);

}
