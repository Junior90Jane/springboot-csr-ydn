package com.ydn.project.model.dto;

import java.sql.Date;
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
public class OrderDto {
	

	private Long ogId;                 // 訂單id
	private String ticketName;         // 訂單名稱
	private String ticketType;         // 訂單類別
	private Integer amount;            // 訂購數量
	private Integer price;             // 訂購金額
	private Integer totalPrice;        // 訂購總金額
	private LocalDateTime createTime;  // 下單時間

}
