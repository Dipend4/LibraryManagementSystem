package com.lms.userdetail.model;

import java.time.LocalDate;

import com.lms.userdetail.Enum.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="detail_tbl")
public class UserDetail {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long detailId;
	
	@Column(nullable=false, length=50)
	private String fullname;
	
	@Column(nullable=false, unique=true, length=15)
	private String phone;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private Gender gender;
	
	@Column(nullable=false, length=100)
	private String address;
	
	@Column(nullable=false)
	private LocalDate dob;
}
