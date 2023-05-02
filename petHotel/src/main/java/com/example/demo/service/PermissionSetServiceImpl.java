package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.PermissionSet;
import com.example.demo.repository.PermissionSetRepository;

@Service
public class PermissionSetServiceImpl implements PermissionSetService {
	
	@Autowired
    private PermissionSetRepository permissionSetRepository;
	
	// Create & Update
	@Override
	public void savePermissionSet(PermissionSet permissionSet) {
		this.permissionSetRepository.save(permissionSet);
		
	}
	
	// Read: All
	@Override
	public List<PermissionSet> getPermissionSets() {
		return permissionSetRepository.findAll();
	}
	
	// Read: by Id
	@Override
	public PermissionSet getPermissionSetById(Integer id) {
		
		Optional <PermissionSet> optional = permissionSetRepository.findById(id);
		
		PermissionSet permissionSet = null;
		
        if (optional.isPresent()) {
        	permissionSet = optional.get();
        } else {
            throw new RuntimeException("PermissionSet not found for id: " + id);
        }
        return permissionSet;
        
	}
	
	// Delete
	@Override
	public void deletePermissionSetById(Integer id) {
		this.permissionSetRepository.deleteById(id);
		
	}

	/**************yen jen**********************/
	@Override
	public PermissionSet findById(Integer id) {
		PermissionSet permissionSet=permissionSetRepository.findById(id).get();
		return permissionSet;
	}
}
