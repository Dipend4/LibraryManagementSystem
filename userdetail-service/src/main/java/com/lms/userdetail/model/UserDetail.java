package com.lms.userdetail.model;

import java.time.LocalDate;

import com.lms.userdetail.Enum.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name="detail_table")
public class UserDetail {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "detail_id")
	 private Long detailId;

	 @Column(nullable = false, length = 30)
	 private String fullname;

	 @Column(nullable = false, unique = true, length = 10)
	 private String phone;

	 @Column(nullable = false, length = 20)
	 private Gender gender;

	 @Column(nullable = false, length = 50)
	 private String address;

	 @Column(nullable = false)
	 private LocalDate dob;
	 
	 @Column(name = "user_id", nullable = false, unique = true)
	 private Long userId;
}
