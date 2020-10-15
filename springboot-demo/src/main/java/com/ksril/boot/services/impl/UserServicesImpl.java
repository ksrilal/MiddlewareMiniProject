package com.ksril.boot.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksril.boot.domain.UserDTO;
import com.ksril.boot.repositories.UserRepository;
import com.ksril.boot.services.UserServices;

@Service
public class UserServicesImpl implements UserServices {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<UserDTO> findAllUsers() {
		List<UserDTO> allUsers = userRepository.findAll();
		return allUsers;
	}

	@Override
	public String saveUser(UserDTO userData) {
		userRepository.save(userData);
		return "Data Saved!";
	}

	@Override
	public String updateUser(UserDTO newUserData) {
		String msg = null;
		if(newUserData.getId() != null) {
			userRepository.save(newUserData);
			msg = "Data Updated!";
		}
		else msg = "Data Not Updated!";
		return msg;
	}

	@Override
	public UserDTO findById(Integer id) {
		return userRepository.findOne(id);
	}

	@Override
	public String deleteUser(Integer id) {
		userRepository.delete(id);
		return "Data Deleted!";
	}
}
