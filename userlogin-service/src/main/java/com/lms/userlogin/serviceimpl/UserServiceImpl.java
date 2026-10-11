package com.lms.userlogin.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lms.userlogin.dto.AdminUpdateDto;
import com.lms.userlogin.dto.UserLoginDto;
import com.lms.userlogin.dto.UserLoginViewDto;
import com.lms.userlogin.dto.UserUpdateDto;
import com.lms.userlogin.service.UserLoginService;

@Service
public class UserServiceImpl implements UserLoginService{

	@Override
	public UserLoginDto addUser(UserLoginDto userDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserLoginViewDto> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(Long userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserUpdateDto updateUser(Long userId, UserUpdateDto userUpdate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AdminUpdateDto updateAdmin(Long userId, AdminUpdateDto adminUpdate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserUpdateDto getByUserId(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AdminUpdateDto getAdminById(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserLoginViewDto getUserLoginById(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
