package com.ksril.boot.services;

import java.util.List;

import com.ksril.boot.domain.UserDTO;

public interface UserServices {

	List<UserDTO> findAllUsers();

	String saveUser(UserDTO userData);

	String updateUser(UserDTO newUserData);

	UserDTO findById(Integer id);

	String deleteUser(Integer id);
}
