package com.ydn.project.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ydn.project.exception.customer.CustomerAlreadyExistsException;
import com.ydn.project.exception.customer.CustomerException;
import com.ydn.project.mapper.CustomerMapper;
import com.ydn.project.model.dto.CustomerDto;
import com.ydn.project.model.entity.Customer;
import com.ydn.project.model.entity.Customer.Gender;
import com.ydn.project.repository.CustomerRepository;
import com.ydn.project.repository.CustomerRepositoryJdbc;
import com.ydn.project.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private CustomerRepositoryJdbc customerRepositoryJdbc;
	
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

	// 新增顧客會員
	@Override
	public void addCustomer(Customer customer) {	
		// 檢查是否已存在相同帳號
		Optional<Customer> optCustomer = customerRepository.findByCtAccount(customer.getCtAccount());
		if(optCustomer.isPresent()) {
			throw new CustomerAlreadyExistsException("新增失敗: 帳號 " + customer.getCtAccount() + " 已存在");
		}
		
		Customer savaedCustomer = customerRepository.save(customer);
        if (savaedCustomer.getCtAccount() == null) {
            throw new CustomerException("無法新增! 自增 ID 未生成");
        }
	}

	@Override
	public void addCustomer(String ctAccount, String ctPassword, Gender gender, String age, String ctEmail, String ctPhone) {
		Customer customer = new Customer();
		customer.setCtAccount(ctAccount);
		customer.setCtPassword(ctPassword);
		customer.setGender(gender);
		customer.setAge(age);
		customer.setCtEmail(ctEmail);
		customer.setCtPhone(ctPhone);
		addCustomer(customer);
	}

}
