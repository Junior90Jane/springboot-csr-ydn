package com.ydn.project.utils;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

import com.ydn.project.model.entity.Customer;
import com.ydn.project.model.entity.Organizer;

import jakarta.annotation.sql.DataSourceDefinition;


public class Hash {
	
	// 產生鹽
	public static String getSalt() {
		SecureRandom secureRandom = new SecureRandom();
		byte[] salt = new byte[16];
		secureRandom.nextBytes(salt);
		return Base64.getEncoder().encodeToString(salt);
	}
	
	// 產生含鹽雜湊 for customerPassword
	public static String getHashForCustomer(String password, String salt) {
		try {
			// 加密演算法: SHA-256
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			// 加鹽
			md.update(salt.getBytes());
			// 進行加密
			byte[] bytes = md.digest(password.getBytes());
			// 將 byte[] 透過 Base64 編碼方便儲存
			return Base64.getEncoder().encodeToString(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// 產生含鹽雜湊 for organizerPassword
	public static String getHashForOrganizer(String password, String salt) {
		try {
			// 加密演算法: SHA-256
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			// 加鹽
			md.update(salt.getBytes());
			// 進行加密
			byte[] bytes = md.digest(password.getBytes());
			// 將 byte[] 透過 Base64 編碼方便儲存
			return Base64.getEncoder().encodeToString(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}