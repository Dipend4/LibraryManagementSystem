package com.lms.studentservice.model;

import java.time.LocalDate;

import com.lms.studentservice.Enum.Course;
import com.lms.studentservice.Enum.Semesters;
import com.lms.studentservice.Enum.Years;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student_tbl")
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    private String studentCode;
    
    @Column(nullable=false)
    private Course course;

    private Semesters semester;
    
    private Years year;

    @Column(nullable=false)
    private LocalDate enrollmentDate;
    
    @Column(name="user_id", nullable=false)
    private Long userId;
    
    @Column(name="detail_id", nullable=false)
    private Long detailId;
    
}