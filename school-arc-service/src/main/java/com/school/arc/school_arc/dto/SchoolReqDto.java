package com.school.arc.school_arc.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchoolReqDto {

	 	@NotBlank(message = "School name is required")
	    @Size(min = 2, max = 100, message = "School name must be between 2 and 100 characters")
	    private String schoolName;

	    @Size(max = 255, message = "School description can be up to 255 characters")
	    private String schoolDesc;

	    @NotBlank(message = "School code is required")
	    @Pattern(regexp = "^[A-Z0-9_]+$", message = "School code must be uppercase alphanumeric (underscores allowed)")
	    private String schoolCode;

	    @NotBlank(message = "Email is required")
	    @Email(message = "Invalid email format")
	    private String email;

	    @NotBlank(message = "Phone number is required")
	    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
	    private String phoneNo;

}
