package com.ydn.project.service;

import java.util.List;
import com.ydn.project.model.dto.CustomerDto;
import com.ydn.project.model.entity.Customer;
import com.ydn.project.model.entity.Customer.Gender;


public interface CustomerService {

	public List<CustomerDto> getAllAdmins();
	public CustomerDto getCustomerByAccount(String ctAccount);
	public void addCustomer(Customer customer);
	public void addCustomer(String ctAccount, String ctPassword, Gender gender, String age, String ctEmail, String ctPhone);

}