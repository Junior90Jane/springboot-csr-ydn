package com.ydn.project.model.dto;

import java.sql.Date;

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
public class OrganizerDto {
	
	
	private Long ogId;         // 主辦方id
	private String ogAccount;  // 主辦方帳號
	private String company;    // 主辦方公司
	private String ogEmail;    // 主辦方電郵
	private String ogPhone;    // 主辦方電話
	private String role;       // 主辦方權限

}
