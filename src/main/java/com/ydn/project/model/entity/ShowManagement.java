package com.ydn.project.model.entity;

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
@Entity
@Table (name = "showmanagement")
public class ShowManagement {
	
	@Id
	private Long smId;           // 節目管理Id
	@Column(nullable = false)
	private String ticketName;   // 節目名稱
	@Column(nullable = false)
	private String adAccount;    // 管理者帳號
	@Column(nullable = false)
	private String ticketType;   // 節目類別
	@Column(nullable = false)
	private LocalDate startTime; // 活動開始時間
	@Column(nullable = false)
	private LocalDate endTime;   // 活動結束時間
	@Column(nullable = false)
	private String status;       // 審核狀態

}
