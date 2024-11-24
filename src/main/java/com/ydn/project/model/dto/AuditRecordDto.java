package com.ydn.project.model.dto;

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
public class AuditRecordDto {
	

	private Long recordId;     // 審核id
	private String adAccount;  // 管理員帳號
	private String ticketName; // 節目名稱
	private String status;     // 審核狀態
	private String text;       // 審核評論
	

}
