package com.ydn.project.model.entity;

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
@Table (name = "auditrecords")
public class AuditRecord {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long recordId;     // 審核id
	@Column(nullable = false, unique = true)
	private String adAccount;  // 管理員帳號
	@Column(nullable = false)
	private String ticketName; // 節目名稱
	@Column(nullable = false)
	private String status;     // 審核狀態
	@Column(nullable = false)
	private String text;       // 審核評論
	

}
