package com.ydn.project.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.ydn.project.model.entity.Organizer;
import com.ydn.project.model.entity.ProgramApplication.ApplicationStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgramApplicationDto {

    private Long applicationId;
    private Organizer organizer;
    private String programName;
    private String programType;
    private Integer ticketQuantity;
    private BigDecimal ticketPrice;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String imageBase64;
    private String description;
    private ApplicationStatus applicationStatus;
    private LocalDateTime applicationTime = LocalDateTime.now();

}
