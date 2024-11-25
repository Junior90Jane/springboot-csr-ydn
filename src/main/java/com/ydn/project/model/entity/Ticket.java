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
@Table (name = "tickets")
public class Ticket {
	
	@Id
	private Long ticketId;        // 票id
	@Column(nullable = false)
	private String ticketName;    // 票名
	@Column(nullable = false)
	private String ticketType;    // 票類別
	private Integer amount;       // 供應張數
	private Integer price;        // 票價
	@Column(nullable = false)
	private LocalDate startTime;  // 活動開始時間
	@Column(nullable = false)
	private LocalDate endTime;    // 活動結束時間
	@Column(nullable = false)
	private String image;        // 圖片
	@Column(nullable = false)
	private String text;          // 節目簡述

}
