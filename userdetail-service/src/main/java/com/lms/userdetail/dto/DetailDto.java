package com.lms.userdetail.dto;

import java.time.LocalDate;

import com.lms.userdetail.Enum.Gender;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DetailDto {

	private Long detailId;

	@NotBlank(message= "Fullname is required!")
	@Size(min=3, max=30, message="Fullname must be 3-30 characters!")
	private String fullname;

	@NotBlank(message="Phone is required!")
	@Pattern(regexp = "^(97|98)\\d{8}$", message = "Phone number must be a valid 10-digit phone number!")
	private String phone;

	@NotNull(message="Gender is required!")
	private Gender gender;

	@NotBlank(message="Address is required!")
	@Size(min=3, max=50, message="Address must be 3-50 characters!")
	private String address;

	@NotNull(message="Date of birth is required!")
	@Past(message="Date of birth must be in the past!")
	private LocalDate dob;
}
