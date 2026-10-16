package com.lms.userlogin.dto;

import com.lms.userlogin.Enum.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserLoginDto {

	private Long userId;
	
	private String username;
	
	@NotBlank(message="Email is required!")
	@Email(message="Please provide  a valid email address!")
	@Size(max=100, message="Email must not exceed 100 characters!")
	private String email;
	
	@NotBlank(message="Password is required!")
	@Size(min=8, max=72, message="Password must be between 8-72 characters!")
	private String password;
	
	@NotNull(message="Role is required!")
	private Role role;
	
	private boolean enabled;
	
}
