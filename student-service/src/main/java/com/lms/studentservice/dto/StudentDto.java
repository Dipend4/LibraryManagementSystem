package com.lms.studentservice.dto;

import java.time.LocalDate;

import com.lms.studentservice.Enum.Course;
import com.lms.studentservice.Enum.Semesters;
import com.lms.studentservice.Enum.Years;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentDto {

    private Long studentId;

    private String studentCode;
   
   @NotNull(message="Course is required!")
    private Course course;

   @NotNull(message="Semester is required!")
    private Semesters semester;
    
   @NotNull(message="Year is required!")
    private Years year;

   @PastOrPresent(message="Enrollment date cannot be in the future!")
   @FutureOrPresent(message="Enrollment date cannot be in the past!")
    private LocalDate enrollmentDate;
}
