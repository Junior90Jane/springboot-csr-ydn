package com.ydn.project.service;

import java.util.List;
import java.util.Optional;

import com.ydn.project.model.dto.CustomerDto;
import com.ydn.project.model.dto.LoginDto;
import com.ydn.project.model.dto.OrganizerDto;
import com.ydn.project.model.entity.Customer;
import com.ydn.project.model.entity.Organizer;

// 會員資料的 CRUD
public interface UserService {
	
	//主辦單位 CRUD
	public List<OrganizerDto> getAllOrganizers();    // 顯示在後台會員管理上
	public Organizer getOrganizerByUsername(String username); // 查詢單筆主辦單位
	public void addOrganizer(Organizer organizer);   // 註冊主辦單位
	public void updateOrganizer(String username, OrganizerDto organizerDto);  // 會員管理中可以更新的資料
	public void deleteOrganizer(String username);   // 後臺的會員管理，可以有刪除主辦單位的功能
	public Optional<OrganizerDto> Organizerlogin(LoginDto loginDto, OrganizerDto organizerDto); // 主辦單位登入

	
	// 購票顧客 CRUD
	public List<CustomerDto> getAllCustomers();   // 顯示在後台會員管理上
	public Customer getCustomerByUsername(String username); // 查詢單筆購票顧客
	public void addCustomer(Customer customer);   // 註冊購票顧客
	public void updateCustomer(String username, CustomerDto customerDto);  // 會員管理中可以更新的資料
	public void daleteCustomer(String username); // 後臺的會員管理，可以有刪除購票顧客的功能
	public Optional<CustomerDto> Customerlogin(LoginDto loginDto, CustomerDto customerDto); // 購票顧客登入

}
