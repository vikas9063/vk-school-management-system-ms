package com.school.arc.school_arc.modal;


import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SCHOOL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class School {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String schoolId;

	@Column(nullable = false)
	private String schoolName;
	private String schoolCode;
	private String schoolDesc;

	@Column(nullable = false)
	private String phoneNo;
	@Column(nullable = false)
	private String emailId;
	
	private String alternatePhoneNo;
	private String alternateEmailId;
	
	
	private String address;
	private String city;
	private String state;
	private String pinCode;
	private String country;
	
	
	private String schoolType;
	private String boardType;
	private String levels;
	private String facilities;
	private Integer establishedOn;
	private String academicSchedule;
	private String admissionPeriod;
	private String extraCurricular;
	
	private String schoolLogo;
	
	
	private boolean isActive;
	private boolean isEnabled;
	
	private String createdBy;
	private Timestamp createdOn;
	private Timestamp updatedOn;
	

}
