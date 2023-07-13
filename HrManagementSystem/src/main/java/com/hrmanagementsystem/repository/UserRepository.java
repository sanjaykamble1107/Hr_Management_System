package com.hrmanagementsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrmanagementsystem.entity.User;



public interface UserRepository extends JpaRepository<User, Integer>{
	
	
	public Optional<User> findByName(String name);
}
