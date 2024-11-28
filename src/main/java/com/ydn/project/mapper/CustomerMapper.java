package com.ydn.project.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ydn.project.model.dto.CustomerDto;
import com.ydn.project.model.entity.Customer;

@Component
public class CustomerMapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Customer toEntity(CustomerDto customerDto) {
		return modelMapper.map(customerDto, Customer.class);
	}
	
	public CustomerDto toDto(Customer customer) {
		return modelMapper.map(customer, CustomerDto.class);
	}

}
