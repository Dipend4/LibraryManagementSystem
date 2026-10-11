package com.lms.userlogin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.userlogin.model.UserLogin;

public interface UserRepository extends JpaRepository<UserLogin, Long>{

	Optional<UserLogin> findByEmail(String email);
	boolean existsByEmail(String email);
	boolean existsByUsername(String username);
}
