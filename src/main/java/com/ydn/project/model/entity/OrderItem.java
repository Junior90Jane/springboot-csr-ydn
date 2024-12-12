package com.ydn.project.model.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;

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
@Entity
@Table (name = "orderitems")
public class OrderItem {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId; // 訂單項目ID

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order; // 所屬訂單 (外鍵)

    @Column(nullable = false, length = 100)
    private String programName; // 節目名稱

    @Column(nullable = false, length = 50)
    private String programType; // 節目類型

    @Column(nullable = false)
    private int quantity; // 購買數量

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice; // 單價

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice; // 總價

}
