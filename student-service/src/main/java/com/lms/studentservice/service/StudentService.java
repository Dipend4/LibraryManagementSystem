package com.lms.studentservice.service;

import java.util.List;

import com.lms.studentservice.dto.StudentDto;
import com.lms.studentservice.dto.StudentUpdateDto;
import com.lms.studentservice.dto.StudentViewDto;

public interface StudentService {

	StudentDto addStudentDto(StudentDto studentDto);
	List<StudentViewDto> getAllStudents();
	StudentUpdateDto updateStudent(Long studentId, StudentUpdateDto studentUpdate);
	void deleteStudent(Long studentId);
	
	StudentUpdateDto getUpdateById(Long studentId);
	StudentViewDto getViewById(Long studentId);
}
