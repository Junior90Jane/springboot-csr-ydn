package com.ydn.project.repository;

import java.util.List;
import java.util.Optional;

import com.ydn.project.model.entity.Customer;

public interface CustomerRepositoryJdbc {
	List<Customer> findAll();
	Optional<Customer> findById(Long adId);
	int save(Customer Customer); 
	int update(Customer Customer);
	int deleteById(Long adId);
}
