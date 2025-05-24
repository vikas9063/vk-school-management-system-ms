package com.school.arc.school_arc.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.school.arc.school_arc.modal.School;

public interface SchoolRepo extends JpaRepository<School, String> {

	Page<School> findAllByIsEnabled(boolean isEnabled, Pageable pageable);
	
}
