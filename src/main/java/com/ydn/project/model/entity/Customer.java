package com.ydn.project.model.entity;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "customers")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ctId; // 顧客id
	@Column(nullable = false, unique = true)
	private String ctAccount; // 顧客帳號
	@Column(nullable = false)
	private String ctPassword; // 顧客密碼
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Gender gender; // 顧客性別
	@Column(nullable = false)
	private String age; // 顧客年齡層
	@Column(nullable = false, unique = true)
	private String ctEmail; // 顧客電郵
	@Column(nullable = false)
	private String ctPhone; // 顧客電話
	private String role; // 顧客權限

	public enum Gender {
		MALE("男性"), FEMALE("女性");

		private final String displayName;

		Gender(String displayName) {
			this.displayName = displayName;
		}

		public String getDisplayName() {
			return displayName;
		}
	}

}
