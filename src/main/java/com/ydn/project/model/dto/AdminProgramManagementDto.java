package com.ydn.project.model.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.ydn.project.model.entity.ProgramApplication;

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
public class AdminProgramManagementDto {
	
    private Long programId;
    private String programName;
    private Integer ticketQuantity;
    private String programType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String imageBase64;
    private String description;
    private ProgramApplication.ApplicationStatus applicationStatus;
    private LocalDateTime applicationTime = LocalDateTime.now();

}
