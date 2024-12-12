package com.ydn.project.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
@Entity
@Table (name = "programApplications")
public class ProgramApplication {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long applicationId;

	    @ManyToOne
	    @JoinColumn(name = "organizer_id", nullable = false)
	    private Organizer organizer;

	    @Column(nullable = false, length = 100)
	    private String programName;

	    @Column(nullable = false, length = 50)
	    private String programType;

	    @Column(nullable = false)
	    private int ticketQuantity;

	    @Column(nullable = false, precision = 10, scale = 2)
	    private BigDecimal ticketPrice;

	    @Column(nullable = false)
	    private LocalDateTime startTime;

	    @Column(nullable = false)
	    private LocalDateTime endTime;

	    @Column(columnDefinition = "LONGTEXT")
	    private String imageBase64;

	    @Column(columnDefinition = "TEXT")
	    private String description;

	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private ApplicationStatus applicationStatus;

	    @Column(nullable = false)
	    private LocalDateTime applicationTime = LocalDateTime.now();

	    public enum ApplicationStatus {
	        PENDING, APPROVED, REJECTED
	    }

}
