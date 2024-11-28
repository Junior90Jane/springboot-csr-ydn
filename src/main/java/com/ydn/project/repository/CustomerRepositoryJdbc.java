package com.ydn.project.repository;

import java.util.List;
import java.util.Optional;

import com.ydn.project.model.entity.Customer;

public interface CustomerRepositoryJdbc {
	List<Customer> findAll();
	Optional<Customer> findByAccount(String adAccount);
	int save(Customer customer); 
	int update(Customer customers);
	int deleteByAccount(String adAccount);
}
