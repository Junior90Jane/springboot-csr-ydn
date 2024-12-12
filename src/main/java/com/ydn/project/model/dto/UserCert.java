package com.ydn.project.model.dto;

import com.ydn.project.model.entity.Customer.Gender;

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
@Table(name = "usercerts")
public class UserCert {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long certId; 
	@Column(nullable = false, unique = true)
	private String username; // 顧客帳號
}
