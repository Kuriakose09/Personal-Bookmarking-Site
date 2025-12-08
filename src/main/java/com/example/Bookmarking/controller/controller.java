package com.example.Bookmarking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Bookmarking.DTO.UserDto;

import com.example.Bookmarking.Service.UserService;



@Controller
public class controller {
	@Autowired
	UserService userService;
	
	@GetMapping("/registration")
	public String registor(Model model) {
		model.addAttribute("message", "Register User!");
		 model.addAttribute("user", new UserDto());
		return "registor";
	}


	@PostMapping("/registration")
	public String saveUser(@ModelAttribute("UserModel") UserDto userDto, Model model) {
	    userService.save(userDto);
	    model.addAttribute("message", "Registered Successfuly!");
	    return "register";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/create")
	public String create() {
		return "create";
	}


}