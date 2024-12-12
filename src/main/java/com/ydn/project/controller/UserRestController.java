package com.ydn.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ydn.project.exception.customer.CustomerException;
import com.ydn.project.exception.organizer.OrganizerException;
import com.ydn.project.model.dto.CustomerDto;
import com.ydn.project.model.dto.OrganizerDto;
import com.ydn.project.model.entity.AdminAccount;
import com.ydn.project.model.entity.Customer;
import com.ydn.project.model.entity.Organizer;
import com.ydn.project.response.ApiResponse;
import com.ydn.project.service.UserService;

import jakarta.validation.Valid;

/**
請求方法 URL 路徑                    功能說明            請求參數              回應
-----------------------------------------------------------------------------------------------------
GET    /rest/user/customers      取得所有購票顧客資料                       成功時顯示所有購票顧客列表
GET    /rest/user/organizers     取得所有主辦單位資料                       成功時顯示所有主辦單位列表
POST   /rest/user/addcustomer    新增購票顧客                            新增完成返回主畫面
POST   /rest/user/addorganizer   新增主辦單位                            新增完成返回主畫面
POST   /rest/user/login          使用者登入           無                 成功時返回成功訊息，無回傳資料。
POST   /rest/user/logout         使用者登出           無                 成功時返回成功訊息，無回傳資料。
PUT    /rest/user/updatecustomer
PUT    /rest/user/updateorganizer
DELETE /rest/user/deletecustomer
DELETE /rest/user/deleteorganizer
*/

@RestController
@RequestMapping("/rest/user")
@CrossOrigin(origins = {"http://localhost:5173"}, allowCredentials = "true")
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	// 取得所有購票顧客資料
	@GetMapping("/customers")
	public ResponseEntity<ApiResponse<List<CustomerDto>>> getCustomers(){
		List<CustomerDto> customers = userService.getAllCustomers();
		String message = customers.isEmpty() ? "customer 查無資料" : "customer 查詢多筆成功";
		return ResponseEntity.ok(ApiResponse.success(message, customers));
	}
	
	// 取得所有主辦單位資料
	@GetMapping("/organizers")
	public ResponseEntity<ApiResponse<List<OrganizerDto>>> getOrganizers(){
		List<OrganizerDto> organizers = userService.getAllOrganizers();
		String message = organizers.isEmpty() ? "organizers 查無資料" : "organizers 查詢多筆成功";
		return ResponseEntity.ok(ApiResponse.success(message, organizers));
	}
	
	// 新增購票顧客
	@PostMapping("/addcustomer")
	public ResponseEntity<ApiResponse<Customer>> addCustomer(@RequestBody Customer customer){
		userService.addCustomer(customer);
		return ResponseEntity.ok(ApiResponse.success("購票顧客：" + customer.getUsername() + " 資料修改成功! ", customer));
	}
	
	// 新增主辦單位
	@PostMapping("/addorganizer")
	public ResponseEntity<ApiResponse<Organizer>> addOrganizer(@RequestBody Organizer organizer){
		userService.addOrganizer(organizer);
		return ResponseEntity.ok(ApiResponse.success("主辦單位：" + organizer.getUsername() + " 資料修改成功! ", organizer));
	}
	
	// 修改購票顧客
	@PutMapping("/updatecustomer")
	public ResponseEntity<ApiResponse<CustomerDto>> updatecustomer(@PathVariable String ctAccount, @RequestBody CustomerDto customerDto){
		userService.updateCustomer(ctAccount, customerDto);
		return ResponseEntity.ok(ApiResponse.success("購票顧客：" + ctAccount + " 資料修改成功! ", customerDto));
	}
	
	// 修改主辦單位
	@PutMapping("/updateorganizer")
	public ResponseEntity<ApiResponse<OrganizerDto>> updateorganizer(@PathVariable String ogAccount, @RequestBody OrganizerDto organizerDto){
		userService.updateOrganizer(ogAccount, organizerDto);
		return ResponseEntity.ok(ApiResponse.success("主辦單位：" + ogAccount + " 資料修改成功! ", organizerDto));
	}

	// 刪除購票顧客
	@DeleteMapping("/deletecustomer")
	public ResponseEntity<ApiResponse<String>> deletecustomer(@PathVariable String ctAccount){
		userService.daleteCustomer(ctAccount);
		return ResponseEntity.ok(ApiResponse.success("購票顧客：" + ctAccount + " 刪除成功! ", ctAccount));
	}
	
	// 刪除主辦單位
	@DeleteMapping("/deleteorganizer")
	public ResponseEntity<ApiResponse<String>> deleteorganizer(@PathVariable String ogAccount){
		userService.deleteOrganizer(ogAccount);
		return ResponseEntity.ok(ApiResponse.success("主辦單位：" + ogAccount + " 刪除成功! ", ogAccount));
	}
	
	
	@ExceptionHandler({CustomerException.class})
	public ResponseEntity<ApiResponse<Void>> handleCustomerExceptions(CustomerException e){
		return ResponseEntity.status(500).body(ApiResponse.error(500, e.getMessage()));
	}
	@ExceptionHandler({OrganizerException.class})
	public ResponseEntity<ApiResponse<Void>> handleOrganizerExceptions(OrganizerException e){
		return ResponseEntity.status(500).body(ApiResponse.error(500, e.getMessage()));
	}
	

	
}
