package com.lms.userdetail.service;

import java.util.List;

import com.lms.userdetail.dto.DetailDto;
import com.lms.userdetail.dto.DetailUpdateDto;
import com.lms.userdetail.dto.DetailViewDto;

public interface DetailService {

	DetailDto addUser(DetailDto detailDto);
	List<DetailViewDto> getAllDetails();
	DetailUpdateDto updateDetail(Long detailId, DetailUpdateDto detailUpdate);
	void deleteDetail(Long detailId);
	
	DetailUpdateDto getUpdateById(Long detailId);
	DetailViewDto getViewById(Long detailId);
}
