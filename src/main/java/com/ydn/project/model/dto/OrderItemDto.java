package com.ydn.project.model.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;

import com.ydn.project.model.entity.Order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class OrderItemDto {
	
    private Long orderItemId; // 訂單項目ID
    private Order order; // 所屬訂單 (外鍵)
    private String programName; // 節目名稱
    private String programType; // 節目類型
    private Integer quantity; // 購買數量
    private BigDecimal unitPrice; // 單價
    private BigDecimal totalPrice; // 總價

}
