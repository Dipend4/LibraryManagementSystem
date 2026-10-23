package com.lms.userdetail.controller;

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

import com.lms.userdetail.dto.DetailDto;
import com.lms.userdetail.dto.DetailUpdateDto;
import com.lms.userdetail.dto.DetailViewDto;
import com.lms.userdetail.service.DetailService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/details")
@RequiredArgsConstructor
public class DetailController {

	private DetailService detailService;
	
	@PostMapping
	public ResponseEntity<DetailDto> addDetails(@Valid @RequestBody DetailDto detailDto){
		
		DetailDto response= detailService.addUser(detailDto);
		URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{detailId")
				.buildAndExpand(response.getDetailId())
				.toUri();
		return ResponseEntity.created(location).body(response);
	}
	
	@GetMapping
	public ResponseEntity<List<DetailViewDto>> getAllDetails(){
		
		List<DetailViewDto> details= detailService.getAllDetails();
		return ResponseEntity.ok(details);
	}
	
	@GetMapping("/{detailId}")
	public ResponseEntity<DetailViewDto> getDetailById(@PathVariable Long detailId){
		
		DetailViewDto details= detailService.getViewById(detailId);
		return ResponseEntity.ok(details);
	}
	
	@PutMapping("/{detailId}/users")
	public ResponseEntity<DetailUpdateDto> updateDetails(@PathVariable Long detailId, @Valid @RequestBody
			DetailUpdateDto detailUpdate){
		
		DetailUpdateDto details= detailService.updateDetail(detailId, detailUpdate);
		return ResponseEntity.ok(details);
		
	}
	
	@DeleteMapping("/{detailId}")
	public ResponseEntity<Void> deleteDetails(@PathVariable Long detailId){
		
		detailService.deleteDetail(detailId);
		return ResponseEntity.noContent().build();
	}
}
