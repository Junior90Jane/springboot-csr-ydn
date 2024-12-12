package com.ydn.project.model.dto;

import com.ydn.project.model.entity.Customer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackForOrganizerDto {
	
    private Long feedbackId;
    private String programName;
    private String programType;
    private Customer.AgeGroup ageGroup;
    private Customer.Gender gender;
    private Integer preferenceLevel;
	

}
