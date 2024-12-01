package com.ydn.project.model.entity;

import java.sql.Date;

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
@Table (name = "organizers")
public class Organizer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ogId;         // 主辦方id
	@Column(nullable = false, unique = true)
	private String ogAccount;  // 主辦方帳號
	@Column(nullable = false)
	private String ogPassword; // 主辦方密碼
	@Column(nullable = false)
	private String company;    // 主辦方公司
	@Column(nullable = false, unique = true)
	private String ogEmail;    // 主辦方電郵
	@Column(nullable = false)
	private String ogPhone;    // 主辦方電話

}
