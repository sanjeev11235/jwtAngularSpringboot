package com.myApp.demoApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myApp.demoApp.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUserName(String username);

}
