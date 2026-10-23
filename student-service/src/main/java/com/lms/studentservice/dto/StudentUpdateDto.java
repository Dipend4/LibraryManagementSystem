package com.lms.studentservice.dto;

import com.lms.studentservice.Enum.Course;
import com.lms.studentservice.Enum.Semesters;
import com.lms.studentservice.Enum.Years;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentUpdateDto {

    private Long studentId;
   
    @NotNull(message="Course is required!")
    private Course course;

    @NotNull(message="Semester is required!")
    private Semesters semester;
    
    @NotNull(message="Year is required!")
    private Years year;

}
