package com.ydn.project.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "eventfeedback")
public class EventFeedback {
	
	@Id
	private Long feedbackId;     // 回饋id
	@Column(nullable = false)
	private String ticketName;   // 票名
	@Column(nullable = false)
	private String ticketType;   // 票類別
	@Column(nullable = false)
	private String gender;       // 顧客性別
	@Column(nullable = false)
	private String age;          // 顧客年齡層
	@Column(nullable = false)
	private Integer liking;      // 喜好程度

}
