package com.ydn.project.service;

import java.util.List;
import java.util.Optional;

import com.ydn.project.model.dto.CustomerDTO;
import com.ydn.project.model.dto.FavoriteCustomerDTO;
import com.ydn.project.model.dto.FavoriteProductDTO;
import com.ydn.project.model.dto.LoginDTO;


public interface CustomerService {
	Optional<CustomerDTO> findByUsername(String username);
	Optional<CustomerDTO> login(LoginDTO loginDTO);
	Optional<CustomerDTO> saveUser(CustomerDTO customerDTO);
	
	// 用戶關注列表(用戶關注那些商品)
	public List<FavoriteProductDTO> getFavoriteProducts(Long customerId);
	
	// 商品關注列表(商品被那些用戶關注)
	public List<FavoriteCustomerDTO> getFavoriteCustomers(Long productId);
	
	// 新增商品關注
	public void addFavoriteProduct(Long customerId, Long productId);
	
	// 移除商品關注
	public void removeFavoriteProduct(Long customerId, Long productId);
	
}
