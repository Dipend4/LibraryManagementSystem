package com.lms.studentservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.studentservice.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

}
