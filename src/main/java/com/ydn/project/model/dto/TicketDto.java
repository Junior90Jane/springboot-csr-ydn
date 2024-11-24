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
public class TicketDto {

	private Long ticketId;        // 票id
	private String ticketName;    // 票名
	private String ticketType;    // 票類別
	private Integer amount;       // 供應張數
	private Integer price;        // 票價
	private LocalDate startTime;  // 活動開始時間
	private LocalDate endTime;    // 活動結束時間
	private Boolean image;        // 圖片上傳
	private String text;          // 節目簡述

}
