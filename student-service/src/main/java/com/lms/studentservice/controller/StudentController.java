package com.lms.studentservice.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lms.studentservice.dto.StudentDto;
import com.lms.studentservice.dto.StudentUpdateDto;
import com.lms.studentservice.dto.StudentViewDto;
import com.lms.studentservice.service.StudentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

	private StudentService studentService;
	
	@PostMapping
	public ResponseEntity<StudentDto> addStudent(@Valid @RequestBody StudentDto studentDto){
		
		StudentDto response= studentService.addStudentDto(studentDto);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{studentId}")
				.buildAndExpand(response.getStudentId())
				.toUri();
		return ResponseEntity.created(location).body(response);
	}
	
	@GetMapping
	public ResponseEntity<List<StudentViewDto>> getAllStudents(){
		
		List<StudentViewDto> students= studentService.getAllStudents();
		return ResponseEntity.ok(students);
	}
	
	@GetMapping("/{studentId}")
	public ResponseEntity<StudentViewDto> getStudentById(@PathVariable Long studnetId){
		
		StudentViewDto students= studentService.getViewById(studnetId);
		return ResponseEntity.ok(students);
	}
	
	@PutMapping("/{studentId}")
	public ResponseEntity<StudentUpdateDto> updateStudent(@PathVariable Long studentId, @Valid @RequestBody
			StudentUpdateDto studentUpdate){
		
		StudentUpdateDto students= studentService.updateStudent(studentId, studentUpdate);
		return ResponseEntity.ok(students);
	}
	
	@DeleteMapping("/{studentId}")
	public ResponseEntity<Void> deleteStudnet(@PathVariable Long studentId){
		
		studentService.deleteStudent(studentId);
		return ResponseEntity.noContent().build();
	}
	
}
