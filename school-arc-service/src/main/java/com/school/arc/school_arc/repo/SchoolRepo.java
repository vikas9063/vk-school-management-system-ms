package com.school.arc.school_arc.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.arc.school_arc.modal.School;

public interface SchoolRepo extends JpaRepository<School, String> {

}
