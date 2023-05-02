package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.PermissionSet;

public interface PermissionSetRepository extends JpaRepository<PermissionSet, Integer> {

}
