package com.example.Bookmarking.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Bookmarking.Models.UserModel;



public interface UserRepository extends JpaRepository<UserModel, Integer> {
	UserModel findByEmail(String email);
}

