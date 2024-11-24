package com.ydn.project.model.dto;

import java.time.LocalDate;

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
public class ShowManagementDto {
	

	private Long smId;           // 節目管理Id
	private String ticketName;   // 節目名稱
	private String adAccount;    // 管理者帳號
	private String ticketType;   // 節目類別
	private LocalDate startTime; // 活動開始時間
	private LocalDate endTime;   // 活動結束時間
	private String status;       // 審核狀態

}
