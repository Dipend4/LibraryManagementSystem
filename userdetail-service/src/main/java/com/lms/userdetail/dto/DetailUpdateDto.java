package com.lms.userdetail.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DetailUpdateDto {


	@NotBlank(message= "Fullname is required!")
	@Size(min=3, max=30, message="Fullname must be 3-30 characters!")
	private String fullname;

	@NotBlank(message="Phone is required!")
	@Pattern(regexp = "^(97|98)\\d{8}$", message = "Phone number must be a valid 10-digit phone number!")
	private String phone;

	@NotBlank(message="Address is required!")
	@Size(min=3, max=50, message="Address must be 3-50 characters!")
	private String address;

}
