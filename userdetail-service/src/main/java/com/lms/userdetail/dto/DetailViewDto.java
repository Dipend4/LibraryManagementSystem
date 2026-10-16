package com.lms.userdetail.dto;

import java.time.LocalDate;

import com.lms.userdetail.Enum.Gender;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DetailViewDto {

	private Long detailId;

	private String fullname;

	private String phone;

	private Gender gender;

	private String address;

	private LocalDate dob;
}
