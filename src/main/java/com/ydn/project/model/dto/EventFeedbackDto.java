package com.ydn.project.model.dto;

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
public class EventFeedbackDto {
	
	private Long feedbackId;     // 回饋id
	private String ticketName;   // 票名
	private String ticketType;   // 票類別
	private String gender;       // 顧客性別
	private String age;          // 顧客年齡層
	private Integer liking;      // 喜好程度

}
