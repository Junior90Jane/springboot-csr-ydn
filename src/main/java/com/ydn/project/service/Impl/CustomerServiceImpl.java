package com.ydn.project.service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ydn.project.exception.ProductNotFoundException;
import com.ydn.project.exception.UserNotFoundException;
import com.ydn.project.model.dto.CustomerDTO;
import com.ydn.project.model.dto.FavoriteCustomerDTO;
import com.ydn.project.model.dto.FavoriteProductDTO;
import com.ydn.project.model.dto.LoginDTO;
import com.ydn.project.model.entity.Customer;
import com.ydn.project.model.entity.Product;
import com.ydn.project.repository.CustomerRepository;
import com.ydn.project.repository.ProductRepository;
import com.ydn.project.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository userRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public Optional<CustomerDTO> findByUsername(String username) {
		Optional<Customer> optCustomer = userRepository.findByUsername(username);
		if(optCustomer.isEmpty()) Optional.empty();
		// 利用 modelMapper 將 User 轉 UserDTO
		CustomerDTO userDTO = modelMapper.map(optCustomer.get(), CustomerDTO.class);
		return Optional.of(userDTO);
	}

	@Override
	public Optional<CustomerDTO> login(LoginDTO loginDTO) {
		Optional<Customer> optCustomer = userRepository.findByUsername(loginDTO.getUsername());
		// 判斷密碼
		if(optCustomer.isPresent() && optCustomer.get().getPassword().equals(loginDTO.getPassword())) {
			return Optional.of(modelMapper.map(optCustomer.get(), CustomerDTO.class));
		} 
		return Optional.empty();
	}

	@Override
	public Optional<CustomerDTO> saveUser(CustomerDTO customerDTO) {
		Customer customer = modelMapper.map(customerDTO, Customer.class);
		customer = customerRepository.save(customer);
		
		return Optional.of(modelMapper.map(customer, CustomerDTO.class));
	}
	
	// 用戶關注列表(用戶關注那些商品)
	@Override
	public List<FavoriteProductDTO> getFavoriteProducts(Long customerId) {
		Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new UserNotFoundException());
		// 該用戶關注的商品集合
		Set<Product> products = customer.getFavoriteProducts();
		// 將 products 集合中每一個 Product 元素轉 FavoriteProductDTO
		return products.stream()
						.map(product -> modelMapper.map(product, FavoriteProductDTO.class)) // 元素轉換
						.toList();
	}
	
	// 商品關注列表(商品被那些用戶關注)
	@Override
	public List<FavoriteCustomerDTO> getFavoriteCustomers(Long productId) {
		Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException());
		// 該商品被那些用戶所關注的集合
		Set<Customer> customers = product.getFavoriteUsers();
		// 將 users 集合中每一個 User 元素轉 FavoriteUserDTO
		return customers.stream()
					.map(customer -> modelMapper.map(customer, FavoriteCustomerDTO.class))
					.toList();
	}
	
	// 新增商品關注
	@Override
	public void addFavoriteProduct(Long customerId, Long productId) {
		Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new UserNotFoundException());
		Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException());
		// 將商品加入到用戶關注清單
		customer.getFavoriteProducts().add(product);
		// 保存關係
		customerRepository.save(customer);
	}
	
	// 移除商品關注
	@Override
	public void removeFavoriteProduct(Long customerId, Long productId) {
		Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new UserNotFoundException());
		Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException());
		// 將商品從用戶關注清單中移除
		customer.getFavoriteProducts().remove(product);
		// 保存關係
		customerRepository.save(customer);
	}
	
	
}
