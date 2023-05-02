package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Branch;
import com.example.demo.repository.BranchRepository;

@Service
public class BranchServiceImpl implements BranchService {
	
	@Autowired
    private BranchRepository branchRepository;
	
	// Create & Update
	@Override
	public void saveBranch(Branch branch) {
		this.branchRepository.save(branch);
		
	}
	
	// Read: All
	@Override
	public List<Branch> getBranches() {
		return branchRepository.findAll();
	}
	
	@Override
	public List<Branch> getAllBranch() {
		return branchRepository.findAll();
	}
	
	// Read: by Id
	@Override
	public Branch getBranchById(Integer id) {
		
		Optional <Branch> optional = branchRepository.findById(id);
		
		Branch branch = null;
		
        if (optional.isPresent()) {
        	branch = optional.get();
        } else {
            throw new RuntimeException("Branch not found for id: " + id);
        }
        return branch;
        
	}
	
	// Delete
	@Override
	public void deleteBranchById(Integer id) {
		this.branchRepository.deleteById(id);
		
	}


	
	
}
