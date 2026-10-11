package com.lms.userlogin.dto;

import java.time.LocalDateTime;

import com.lms.userlogin.Enum.Role;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserLoginViewDto {


	private Long userId;
	
	private String username;

	private String email;

	private String password;
	
	private Role role;
	
	private boolean enabled;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
}
