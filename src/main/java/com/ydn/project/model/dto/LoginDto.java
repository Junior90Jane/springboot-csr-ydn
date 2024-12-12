package com.ydn.project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
	
	private String username;
	private String passwordHash;
	private Boolean isLoggedIn; // 是否登入成功 ?

}
