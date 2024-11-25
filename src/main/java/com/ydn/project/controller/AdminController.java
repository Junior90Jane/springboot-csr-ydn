package com.ydn.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ydn.project.model.dto.AdminDto;
import com.ydn.project.response.ApiResponse;
import com.ydn.project.service.AdminService;

import jakarta.validation.Valid;

/**
請求方法 URL 路徑               功能說明          請求參數                                 回應
-----------------------------------------------------------------------------------------------------
GET    /rest/admin          取得所有管理員列表   無                                     成功時返回所有房間的列表及成功訊息。
GET    /rest/admin/{adId}   取得指定管理員資料   adId (路徑參數，房間 ID)                    成功時返回指定房間資料及成功訊息。
POST   /rest/admin          新增管理員        請求體包含 roomDto                        成功時返回成功訊息，無回傳資料。
PUT    /rest/admin/{adId}   更新指定管理員資料   adId (路徑參數，房間 ID)，請求體包含 adminDto   成功時返回成功訊息，無回傳資料。
DELETE /rest/admin/{adId}   刪除指定管理員      adId (路徑參數，房間 ID)                   成功時返回成功訊息，無回傳資料。
*/

@RestController
@RequestMapping("/rest/admin")
@CrossOrigin(origins = {"http://localhost:9090"}, allowCredentials = "true")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	
	// 新增管理員
	@PostMapping
	public ResponseEntity<ApiResponse<AdminDto>> addAdmin(@RequestBody AdminDto adminDto){
		adminService.addAdmin(adminDto);
		return ResponseEntity.ok(ApiResponse.success("Room 新增成功", adminDto));
	}


}
