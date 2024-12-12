package com.ydn.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ydn.project.exception.customer.CustomerNotFoundException;
import com.ydn.project.model.dto.CustomerDto;
import com.ydn.project.model.dto.UserCert;
import com.ydn.project.model.entity.Customer;
import com.ydn.project.model.entity.Organizer;
import com.ydn.project.repository.CustomerRepository;
import com.ydn.project.utils.Hash;

@Component
@Service // 憑證服務
public class CertService {
	
	@Autowired
	private UserService userService;
	
	// 購票顧客登入成功後，可以取得憑證
	public UserCert getCustomerCert(String username, String password) {
		
		// 先確認此人是否存在 ?
		Customer customer = userService.getCustomerByUsername(username);
		if(customer == null) {
			throw new CustomerNotFoundException("找不到此購票顧客帳號: " + username);
		}
		
		// 比對密碼
		String ctPasswordHash = Hash.getHashForCustomer(password, customer.getSalt());
		if(!ctPasswordHash.equals(customer.getPasswordHash())) {
			throw new RuntimeException("密碼不符!");
		}
		
		// 簽發憑證
		UserCert userCert = new UserCert();
		userCert.setCertId(userCert.getCertId());
		userCert.setUsername(username);
		return userCert;
	}
	
	// 主辦單位登入成功後，可以取得憑證
	public UserCert getOrganizerCert(String username, String password) {
		
		// 先確認此人是否存在 ?
		Organizer organizer = userService.getOrganizerByUsername(username);
		if(organizer == null) {
			throw new CustomerNotFoundException("找不到此主辦單位帳號: " + username);
		}
		
		// 比對密碼
		String ogPasswordHash = Hash.getHashForOrganizer(password, organizer.getSalt());
		if(!ogPasswordHash.equals(organizer.getPasswordHash())) {
			throw new RuntimeException("密碼不符!");
		}
		
		// 簽發憑證
		UserCert userCert = new UserCert();
		userCert.setCertId(userCert.getCertId());
		userCert.setUsername(username);
		return userCert;
	}

}
