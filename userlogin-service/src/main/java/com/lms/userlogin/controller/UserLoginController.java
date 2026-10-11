package com.lms.userlogin.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.userlogin.dto.UserLoginDto;
import com.lms.userlogin.service.UserLoginService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserLoginController {

	private final UserLoginService userService;
	
	@PostMapping
	public ResponseEntity<UserLoginDto> addUser(@Valid @RequestBody UserLoginDto userdDto){
		
		UserLoginDto response= userService.addUser(userdDto);
		URI location= URI.create("/api/v1/users" +response.getUserId());
		return ResponseEntity.created(location).body(response);
	}
}
