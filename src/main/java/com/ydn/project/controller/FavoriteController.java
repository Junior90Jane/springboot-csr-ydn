package com.ydn.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ydn.project.aop.CheckUserSession;
import com.ydn.project.model.dto.FavoriteCustomerDTO;
import com.ydn.project.model.dto.CustomerDTO;
import com.ydn.project.model.dto.FavoriteProductDTO;
import com.ydn.project.response.ApiResponse;
import com.ydn.project.service.CustomerService;

import jakarta.servlet.http.HttpSession;
import lombok.Delegate;

@RestController
@RequestMapping("/favorites")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class FavoriteController {
	
	@Autowired
	private CustomerService customerService;
	
	// 獲取用戶關注清單
	@GetMapping
	@CheckUserSession
	public ResponseEntity<ApiResponse<List<FavoriteProductDTO>>> getFavoriteProducts(HttpSession session) {
		Long userId = ((CustomerDTO)session.getAttribute("customerDTO")).getId();
		List<FavoriteProductDTO> favorites = customerService.getFavoriteProducts(userId);
		return ResponseEntity.ok(ApiResponse.success("獲取關注成功清單", favorites));
	}
	
	// 獲取商品被關注清單
	@GetMapping("/product/{productId}")
	public ResponseEntity<ApiResponse<List<FavoriteCustomerDTO>>> getFavoriteUsers(@PathVariable Long productId) {
		List<FavoriteCustomerDTO> users = customerService.getFavoriteCustomers(productId);
		return ResponseEntity.ok(ApiResponse.success("獲取商品被關注清單清單", users));
	}
	
	// 加入關注
	@PostMapping("/{productId}")
	public ResponseEntity<ApiResponse<String>> addFavorite(@PathVariable Long productId, HttpSession session) {
		Long userId = ((CustomerDTO)session.getAttribute("userDTO")).getId();
		customerService.addFavoriteProduct(userId, productId);
		return ResponseEntity.ok(ApiResponse.success("加入關注成功", "商品已加入關注"));
	}
	
	// 取消關注
	@DeleteMapping("/{productId}")
	public ResponseEntity<ApiResponse<String>> removeFavorite(@PathVariable Long productId, HttpSession session) {
		Long userId = ((CustomerDTO)session.getAttribute("userDTO")).getId();
		customerService.removeFavoriteProduct(userId, productId);
		return ResponseEntity.ok(ApiResponse.success("取消關注成功", "商品已取消關注"));
	}
	
}













