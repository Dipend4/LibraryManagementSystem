package com.lms.studentservice.dto;

import java.time.LocalDate;

import com.lms.studentservice.Enum.Course;
import com.lms.studentservice.Enum.Semesters;
import com.lms.studentservice.Enum.Years;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentViewDto {


    private Long studentId;

    private String studentCode;
   
    private Course course;

    private Semesters semester;
    
    private Years year;

    private LocalDate enrollmentDate;
}
