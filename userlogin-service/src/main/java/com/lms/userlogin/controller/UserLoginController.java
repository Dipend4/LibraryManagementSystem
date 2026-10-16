package com.lms.userlogin.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lms.userlogin.dto.AdminUpdateDto;
import com.lms.userlogin.dto.UserLoginDto;
import com.lms.userlogin.dto.UserLoginViewDto;
import com.lms.userlogin.dto.UserUpdateDto;
import com.lms.userlogin.service.UserLoginService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserLoginController {

	private final UserLoginService userService;
	
	//create
	@PostMapping
	public ResponseEntity<UserLoginDto> addUser(@Valid @RequestBody UserLoginDto userdDto){
		
		UserLoginDto response= userService.addUser(userdDto);
		URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}")
				.buildAndExpand(response.getUserId())
				.toUri();
		return ResponseEntity.created(location).body(response);
	}
	
	
	//read: get all users
	@GetMapping
	public ResponseEntity<List<UserLoginViewDto>> getAllUsers(){
		
		List<UserLoginViewDto> users= userService.getAllUsers();
		return ResponseEntity.ok(users);
	}
	
	//read: get user by id
	@GetMapping("/{userId}")
	public ResponseEntity<UserLoginViewDto> getUserById(@PathVariable Long userId){
		
		UserLoginViewDto user= userService.getUserLoginById(userId);
		return ResponseEntity.ok(user);
	}
	
	//update
	@PutMapping("/{userId}/admin")
	public ResponseEntity<AdminUpdateDto> updateAdmin(@PathVariable Long userId, @Valid
			@RequestBody AdminUpdateDto adminUpdate){
		
		AdminUpdateDto admin= userService.updateAdmin(userId, adminUpdate);
		return ResponseEntity.ok(admin);
	}
	
	
	@PutMapping("/{userId}/account")
	public ResponseEntity<UserUpdateDto> updateUser(@PathVariable Long userId, @Valid
			@RequestBody UserUpdateDto userUpdate){
		
		UserUpdateDto user= userService.updateUser(userId, userUpdate);
		return ResponseEntity.ok(user);
	}
	
	//Delete
	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deleteUsers(@PathVariable Long userId){
		
		userService.deleteUser(userId);
		return ResponseEntity.noContent().build();
	}
}
