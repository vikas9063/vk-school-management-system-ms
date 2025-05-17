package com.school.arc.school_arc.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.arc.school_arc.dto.Constants;
import com.school.arc.school_arc.dto.SchoolReqDto;
import com.school.arc.school_arc.service.SchoolService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/school")
public class Controller {

	@Autowired
	private SchoolService schoolService;

	protected String getUsername(HttpServletRequest request) {
		String username = (String) request.getAttribute(Constants.LOGGED_IN_USER);
		if (username == null) {
			throw new RuntimeException("Invalid User");
		}
		return username;
	}

	@PostMapping("/add")
	public ResponseEntity<Map<String, Object>> addSchool(@Valid @RequestBody SchoolReqDto reqDto,
			HttpServletRequest request) {

		return ResponseEntity.ok(this.schoolService.createSchool(reqDto, getUsername(request)));
	}

	@PostMapping("/get-all")
	public ResponseEntity<Map<String, Object>> getAllSchools() {

		return ResponseEntity.ok(this.schoolService.getAllSchools());
	}

}
