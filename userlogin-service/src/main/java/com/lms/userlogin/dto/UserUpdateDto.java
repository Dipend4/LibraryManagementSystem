package com.lms.userlogin.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserUpdateDto {
	
	@NotBlank(message="Email is required!")
	@Email(message="Please provide  a valid email address!")
	@Size(max=100, message="Email must not exceed 100 characters!")
	private String email;
	
}
