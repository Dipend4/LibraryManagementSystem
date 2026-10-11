package com.lms.userlogin.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.lms.userlogin.Enum.Role;

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
@Table(name="userLogin_tbl")
public class UserLogin {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long userId;
	
	@Column(nullable=false, unique=true, length=50)
	private String username;
	
	@Column(nullable=false, unique=true, length=100)
	private String email;
	
	@Column(nullable=false, length=255)
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private Role role;
	
	@Column(nullable=false)
	private boolean enabled;
	
	@CreationTimestamp
	@Column(nullable=false, updatable=false)
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	@Column(nullable=false)
	private LocalDateTime updatedAt;
}
