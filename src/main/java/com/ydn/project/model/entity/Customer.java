package com.ydn.project.model.entity;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "customers")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ctId;          // 顧客id
	@Column(nullable = false, unique = true)
	private Integer ctAccount;  // 顧客帳號
	@Column(nullable = false)
	private Integer ctPassword; // 顧客密碼
	@Column(nullable = false)
	private String gender;      // 顧客性別
	@Column(nullable = false)
	private String age;         // 顧客年齡層
	@Column(nullable = false, unique = true)
	private String ctEmail;     // 顧客電郵
	@Column(nullable = false)
	private String ctPhone;     // 顧客電話
	@Column(nullable = false)
	private String role;        // 顧客權限

}
