package com.ksril.boot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ksril.boot.domain.UserDTO;

public interface UserRepository extends JpaRepository<UserDTO, Integer>{

}
