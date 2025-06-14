package com.school.arc.school_arc.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.school.arc.school_arc.dto.PagedResponseDto;
import com.school.arc.school_arc.dto.SchoolReqDto;
import com.school.arc.school_arc.modal.School;
import com.school.arc.school_arc.repo.SchoolRepo;

@Service
public class SchoolService {

	@Autowired
	private SchoolRepo schoolRepo;

	public Map<String, Object> responseBuild(Object object, String message) {
		Map<String, Object> map = new HashMap<>();
		map.put("message", message);
		map.put("status", "SUCCESS");
		map.put("result", object);
		return map;
	}

	public Map<String, Object> createSchool(SchoolReqDto dto, String userName) {

		School school = new School();
		school.setSchoolCode(dto.getSchoolCode());
		school.setSchoolName(dto.getSchoolName());
		school.setSchoolDesc(dto.getSchoolDesc());
		school.setEmailId(dto.getEmail());
		school.setPhoneNo(dto.getPhoneNo());

		school.setActive(true);
		school.setEnabled(false);

		school.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
		school.setCreatedOn(new Timestamp(System.currentTimeMillis()));
		school.setCreatedBy(userName);
		try {
			school = this.schoolRepo.save(school);
			return responseBuild(school, "School Created Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error in creating school");
		}
	}

	public Map<String, Object> getAllSchools(int pageNo, int pageSize, String sortBy, String sortDir, String type) {

		Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Page<School> page;
		
		if(type.equalsIgnoreCase("Enabled")) {
			page = schoolRepo.findAllByIsEnabled(true, pageable);
		}else if(type.equalsIgnoreCase("Disabled")) {
			page = schoolRepo.findAllByIsEnabled(false, pageable);
		}else {
			page= schoolRepo.findAll(pageable);
		}
		 
		
		return responseBuild(new PagedResponseDto<School>(
				 page.getContent(),
				 page.getNumber(),
				 page.getSize(),
				 page.getTotalElements(),
				 page.getTotalPages(),
				 page.isLast(),
				 page.isFirst()
				), "All School Information fetched successfully");
	}

}
