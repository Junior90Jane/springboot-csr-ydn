package com.ydn.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ydn.project.model.dto.AdminDto;
import com.ydn.project.model.entity.Admin;
import com.ydn.project.model.entity.Customer;
import com.ydn.project.model.entity.Organizer;
import com.ydn.project.response.ApiResponse;
import com.ydn.project.service.AdminService;
import com.ydn.project.service.CustomerService;
import com.ydn.project.service.OrganizerService;

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
@RequestMapping("/rest/register")
@CrossOrigin(origins = {"http://localhost:9090"}, allowCredentials = "true")
public class RegisterRestController {
	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private OrganizerService organizerService;
	@Autowired
	private AdminService adminService;
	
	
	
	// 新增購票顧客
	@PostMapping("/addCustomer")
	public ResponseEntity<ApiResponse<Customer>> appendCustomer(@RequestBody @Valid Customer customer){
		customerService.addCustomer(customer);
		return ResponseEntity.ok(ApiResponse.success("Customer 新增成功! ", customer));
	}
	
	
	// 新增主辦單位
	@PostMapping("/addOrganizer")
	public ResponseEntity<ApiResponse<Organizer>> appendOrganizer(@RequestBody @Valid Organizer organizer){
		organizerService.addOrganizer(organizer);
		return ResponseEntity.ok(ApiResponse.success("organizer 新增成功! ", organizer));
	}
	
	// 取得所有管理員
	@GetMapping
	public ResponseEntity<ApiResponse<List<AdminDto>>> getAdmins(){
		List<AdminDto> adminDtos = adminService.getAllAdmins();
		String message = adminDtos.isEmpty() ? "Admin 查無資料" : "Admin 查詢多筆成功";
		return ResponseEntity.ok(ApiResponse.success(message, adminDtos));
	}
	
	// 取得單筆管理員
	@GetMapping("/{adId}")
	public ResponseEntity<ApiResponse<AdminDto>> getAdmin(@PathVariable Long adId){
		AdminDto adminDto = adminService.getAdminById(adId);
		return ResponseEntity.ok(ApiResponse.success("查詢單筆成功", adminDto));
	}
	
	
	// 新增管理員
	@PostMapping
	public ResponseEntity<ApiResponse<Admin>> appendAdmin(@RequestBody @Valid Admin admin){
		adminService.addAdmin(admin);
		return ResponseEntity.ok(ApiResponse.success("Admin 新增成功", admin));
	}

}
