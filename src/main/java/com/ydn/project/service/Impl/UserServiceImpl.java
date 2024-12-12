package com.ydn.project.service.Impl;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ydn.project.exception.customer.CustomerAlreadyExistsException;
import com.ydn.project.exception.customer.CustomerException;
import com.ydn.project.exception.customer.CustomerNotFoundException;
import com.ydn.project.exception.organizer.OrganizerAlreadyExistsException;
import com.ydn.project.exception.organizer.OrganizerException;
import com.ydn.project.exception.organizer.OrganizerNotFoundException;
import com.ydn.project.model.dto.CustomerDto;
import com.ydn.project.model.dto.LoginDto;
import com.ydn.project.model.dto.OrganizerDto;
import com.ydn.project.model.entity.Customer;
import com.ydn.project.model.entity.Organizer;
import com.ydn.project.repository.CustomerRepository;
import com.ydn.project.repository.CustomerRepositoryJdbc;
import com.ydn.project.repository.OrganizerRepository;
import com.ydn.project.repository.OrganizerRepositoryJdbc;
import com.ydn.project.service.UserService;

@Component
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private OrganizerRepository organizerRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private OrganizerRepositoryJdbc organizerRepositoryJdbc;
	@Autowired
	private CustomerRepositoryJdbc customerRepositoryJdbc;
	
	/**
	 * 主辦單位 CRUD
	 * 
	 **/
	
	@Override
	public List<OrganizerDto> getAllOrganizers() {
		return organizerRepository.findAll()
				.stream()
				.map(organizer -> modelMapper.map(organizer, OrganizerDto.class))
				.collect(toList());
	}

	@Override
	public void addOrganizer(Organizer organizer) {
		// 檢查是否已存在相同的帳號
		Optional<Organizer> optOrganizer = organizerRepository.findByUsername(organizer.getUsername());
		if(optOrganizer.isPresent()) {
			throw new OrganizerAlreadyExistsException("新增失敗! 帳號: "+ organizer.getUsername() +" 已存在。");
		}
		
		// 儲存資料，DB 會自動生成 ID
		Organizer savedOrganizer = organizerRepository.save(organizer);
		if(savedOrganizer.getUsername() == null) {
			throw new OrganizerException("無法新增! 帳號不得為空值");
		}
	}

	@Override
	public void updateOrganizer(String username, OrganizerDto organizerDto) {
	    Optional<Organizer> OptOrganizer = organizerRepository.findByUsername(username);
	    if (OptOrganizer.isEmpty()) {
	        throw new OrganizerException("更新失敗! 帳號: " + username + " 不存在。");
	    }
	    
	    Organizer existingOrganizer = OptOrganizer.get();
	    modelMapper.map(organizerDto, existingOrganizer);

	    int rowCount = organizerRepositoryJdbc.update(existingOrganizer);
	    if (rowCount == 0) {
	        throw new OrganizerException("更新失敗! 無法更新帳號: " + username);
	    }
	}

	@Override
	public void deleteOrganizer(String username) {
		int rowcount = organizerRepository.deleteByUsername(username);
		if(rowcount == 0) {
			throw new OrganizerException("無任何帳號被刪除! ");
		}
	}

	@Override
	public Organizer getOrganizerByUsername(String username) {
		Organizer organizer = organizerRepository.findByUsername(username)
								.orElseThrow(() -> new OrganizerNotFoundException("找不到此主辦單位，帳號: " + username));
		return organizer;
	}
	
	@Override
	public Optional<OrganizerDto> Organizerlogin(LoginDto loginDto, OrganizerDto organizerDto) {
		Optional<Organizer> optOrganizer = organizerRepository.findByUsername(organizerDto.getUsername());
		// 判斷密碼
		if(optOrganizer.isPresent() && optOrganizer.get().getPasswordHash().equals(loginDto.getPasswordHash())) {
			return Optional.of(modelMapper.map(optOrganizer.get(), OrganizerDto.class));
		} 
		return Optional.empty();
	}

	/**
	 * 購票顧客 CRUD
	 * 
	 **/
	
	@Override
	public List<CustomerDto> getAllCustomers() {
	    return customerRepository.findAll()
	            .stream()
	            .map(customer -> modelMapper.map(customer, CustomerDto.class))
	            .collect(toList());
	}

	@Override
	public void addCustomer(Customer customer) {
	    Optional<Customer> optCustomer = customerRepository.findByUsername(customer.getUsername());
	    if (optCustomer.isPresent()) {
	        throw new CustomerAlreadyExistsException("新增失敗! 帳號: " + customer.getUsername() + " 已存在。");
	    }

	    Customer savedCustomer = customerRepository.save(customer);
	    if (savedCustomer.getUsername() == null) {
	        throw new CustomerException("無法新增! 帳號不得為空值");
	    }
	}

	@Override
	public void updateCustomer(String username, CustomerDto customerDto) {
	    Optional<Customer> optCustomer = customerRepository.findByUsername(username);
	    if (optCustomer.isEmpty()) {
	        throw new CustomerException("更新失敗! 帳號: " + username + " 不存在。");
	    }

	    Customer existingCustomer = optCustomer.get();
	    modelMapper.map(customerDto, existingCustomer);
	    

	    int rowCount = customerRepositoryJdbc.update(existingCustomer);
	    if (rowCount == 0) {
	        throw new CustomerException("更新失敗! 無法更新帳號: " + username);
	    }
	}

	@Override
	public void daleteCustomer(String username) {
	    int rowCount = customerRepository.deleteByUsername(username);
	    if (rowCount == 0) {
	        throw new CustomerException("刪除失敗! 帳號: " + username + " 不存在。");
	    }
	}


	@Override
	public Customer getCustomerByUsername(String username) {
		Customer customer = customerRepository.findByUsername(username)
								.orElseThrow(() -> new CustomerNotFoundException("找不到此購票顧客，帳號: " + username));
		return customer;
	}

	@Override
	public Optional<CustomerDto> Customerlogin(LoginDto loginDto, CustomerDto customerDto) {
		Optional<Customer> optCustomer = customerRepository.findByUsername(customerDto.getUsername());
		// 判斷密碼
		if(optCustomer.isPresent() && optCustomer.get().getPasswordHash().equals(loginDto.getPasswordHash())) {
			return Optional.of(modelMapper.map(optCustomer.get(), CustomerDto.class));
		} 
		return Optional.empty();
	}
}
