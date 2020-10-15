package com.ksril.boot.controllers;

import java.util.List;

import com.ksril.boot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ksril.boot.domain.UserDTO;
import com.ksril.boot.services.UserServices;

@RestController
@RequestMapping("/user")
public class User {

	@Autowired
	private UserServices userServices;
	
	@GetMapping("/all")
	public @ResponseBody
	List<UserDTO> allUsers() {
		return userServices.findAllUsers();
	}
	
	@PostMapping("/add")
	public String addUser(@RequestBody UserDTO userData) {
		return userServices.saveUser(userData);
	}
	
	@PutMapping("/update")
	public String updateUser(@RequestBody UserDTO newUserData) {
		return userServices.updateUser(newUserData);
	}
	
	@GetMapping("/find/{id}")
	public UserDTO getUserById(@PathVariable Integer id) {
		return userServices.findById(id);
	}

	@DeleteMapping("/delete")
	public String deleteUser(@RequestParam() Integer id) {
		return userServices.deleteUser(id);
	}



}
