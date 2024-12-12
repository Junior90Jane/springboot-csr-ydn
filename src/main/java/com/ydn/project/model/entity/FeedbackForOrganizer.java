package com.ydn.project.model.entity;

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
@Entity
@Table (name = "feedbackfororganizer")
public class FeedbackForOrganizer {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedbackId;

    @Column(nullable = false, length = 100)
    private String programName;

    @Column(nullable = false, length = 50)
    private String programType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Customer.AgeGroup ageGroup;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Customer.Gender gender;

    @Column(nullable = false)
    private Integer preferenceLevel;
	

}
