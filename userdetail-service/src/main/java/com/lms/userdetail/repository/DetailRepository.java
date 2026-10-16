package com.lms.userdetail.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.userdetail.model.UserDetail;

public interface DetailRepository extends JpaRepository<UserDetail, Long>{

}
