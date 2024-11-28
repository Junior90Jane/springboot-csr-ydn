package com.ydn.project.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ydn.project.model.dto.CustomerDto;
import com.ydn.project.model.entity.Customer;
import com.ydn.project.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

	
	
	@Override
	public List<CustomerDto> getAllAdmins() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerDto getCustomerByAccount(String ctAccount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addCustomer(String ctAccount, String ctPassword, String gender, String age, String ctEmail, String ctPhone) {
		// TODO Auto-generated method stub
		
	}

}
