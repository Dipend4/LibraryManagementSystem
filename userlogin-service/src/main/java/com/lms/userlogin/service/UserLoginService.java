package com.lms.userlogin.service;

import java.util.List;

import com.lms.userlogin.dto.AdminUpdateDto;
import com.lms.userlogin.dto.UserLoginDto;
import com.lms.userlogin.dto.UserLoginViewDto;
import com.lms.userlogin.dto.UserUpdateDto;

public interface UserLoginService {

	UserLoginDto addUser(UserLoginDto userDto);
	List<UserLoginViewDto> getAllUsers();
	void deleteUser(Long userId);
	UserUpdateDto updateUser(Long userId, UserUpdateDto userUpdate);
	AdminUpdateDto updateAdmin(Long userId, AdminUpdateDto adminUpdate);
	
	UserUpdateDto getByUserId(Long userId);
	AdminUpdateDto getAdminById(Long userId);
	
	UserLoginViewDto getUserLoginById(Long userId);
}
