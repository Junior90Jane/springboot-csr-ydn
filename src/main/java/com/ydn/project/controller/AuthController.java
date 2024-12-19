package com.ydn.project.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ydn.project.model.dto.CustomerDTO;
import com.ydn.project.model.dto.LoginDTO;
import com.ydn.project.model.entity.Customer;
import com.ydn.project.response.ApiResponse;
import com.ydn.project.service.CustomerService;

import jakarta.servlet.http.HttpSession;

/*
 * WEB REST API
 * ----------------------------------
 * Servlet-Path: /auth
 * ----------------------------------
 * POST /login      登入
 * GET  /logout     登出
 * GET  /isLoggedIn 判斷目前的連線是否有登入
 * */

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class AuthController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<LoginDTO>> login(@RequestBody LoginDTO loginDTO, HttpSession session) {
		// login 判斷比對
		Optional<CustomerDTO> optUserDTO = customerService.login(loginDTO);
		if(optUserDTO.isEmpty()) {
			return ResponseEntity.status(404).body(ApiResponse.error(404, "登入失敗"));
		}
		// 存入 HttpSession 中
		session.setAttribute("userDTO", optUserDTO.get());
		return ResponseEntity.ok(ApiResponse.success("登入成功", null));
	}
	
	@GetMapping("/logout")
	public ResponseEntity<ApiResponse<String>> logout(HttpSession session) {
		session.invalidate(); // session 失效
		return ResponseEntity.ok(ApiResponse.success("登出結果", "登出成功"));
	}
	
	@GetMapping("/isLoggedIn")
	public ResponseEntity<ApiResponse<LoginDTO>> isLoggedIn(HttpSession session) {
		CustomerDTO userDTO = (CustomerDTO)session.getAttribute("userDTO");
		LoginDTO loginDTO = new LoginDTO();
		if(userDTO == null) {
			loginDTO.setIsLoggedIn(false);
			return ResponseEntity.ok(ApiResponse.success("無登入資訊", loginDTO));
		} 
		loginDTO.setIsLoggedIn(true);
		return ResponseEntity.ok(ApiResponse.success("此人已登入資訊", loginDTO));
	}
	
}
