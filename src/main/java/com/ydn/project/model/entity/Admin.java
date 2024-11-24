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
@Table (name = "admins")
public class Admin {
	
	@Id
	private Long adId;         // 管理者ID
	@Column(nullable = false)
	private String adAccount;  // 管理者帳號
	@Column(nullable = false)
	private String adPassword; // 管理者密碼

}
