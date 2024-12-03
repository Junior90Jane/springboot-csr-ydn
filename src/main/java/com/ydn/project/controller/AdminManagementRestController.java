package com.ydn.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ydn.project.model.dto.OrganizerDto;
import com.ydn.project.response.ApiResponse;
import com.ydn.project.service.OrganizerService;

/**
請求方法    URL 路徑                                  功能說明            請求參數              回應
-----------------------------------------------------------------------------------------------------
GET      /rest/admainmanagement/user             取得所有會員資訊        無                 成功時返回所有會員資訊列表
GET      /rest/admainmanagement/show             取得所有節目資訊        無                 成功時返回所有節目資訊列表
GET      /rest/admainmanagement/Audit            取得所有審核紀錄資訊      無                 成功時返回所有審核紀錄列表
POST     /rest/admainmanagement/Audit            管理員撰寫審核紀錄       無                 成功時返回所有審核紀錄列表
PUT      /rest/admainmanagement/user/{ctAccount} 修改指定購票顧客資訊      ctAccount         成功時返回所有會員資訊列表
PUT      /rest/admainmanagement/user/{ogAccount} 修改指定主辦單位資訊      ogAccount         成功時返回所有會員資訊列表
PUT      /rest/admainmanagement/show/{smId}      修改指定主辦單位資訊      ogAccount         成功時返回所有會員資訊列表
DELETE   /rest/admin          新增管理員        請求體包含 roomDto                        成功時返回成功訊息，無回傳資料。
DELETE   /rest/admin/{adId}   更新指定管理員資料   adId (路徑參數，房間 ID)，請求體包含 adminDto   成功時返回成功訊息，無回傳資料。
*/

@RestController
@RequestMapping("/rest/admainmanagement")
@CrossOrigin(origins = {"http://localhost:9090"}, allowCredentials = "true")
public class AdminManagementRestController {
	
	@Autowired
	private OrganizerService organizerService;
	
	// 取得所有主辦單位列表
	@GetMapping
	public ResponseEntity<ApiResponse<List<OrganizerDto>>> getorganizers() {
		List<OrganizerDto> organizerDtos = organizerService.getAllOrganizers();
		String message = organizerDtos.isEmpty() ? "Room 查無資料" : "Room 查詢多筆成功";
		return ResponseEntity.ok(ApiResponse.success(message, organizerDtos));
	}

}
