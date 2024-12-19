package com.ydn.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ydn.project.aop.CheckUserSession;
import com.ydn.project.model.dto.CustomerDTO;
import com.ydn.project.model.dto.OrderDTO;
import com.ydn.project.model.dto.OrderItemDTO;
import com.ydn.project.response.ApiResponse;
import com.ydn.project.service.OrderService;

import jakarta.servlet.http.HttpSession;

/*
 * WEB REST API
 * ----------------------------------
 * Servlet-Path: /orders
 * ----------------------------------
 * GET   /         查詢該使用者所有商品(多筆)
 * POST  /checkout 該使用者進行結帳
 * */

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping
	@CheckUserSession
	public ResponseEntity<ApiResponse<List<OrderDTO>>> getAllOrders(HttpSession session) {
		CustomerDTO customerDTO = (CustomerDTO) session.getAttribute("customerDTO");
		List<OrderDTO> orderDTOs = orderService.findOrdersByCustomerId(customerDTO.getId());
		return ResponseEntity.ok(ApiResponse.success("查詢成功", orderDTOs));
	}
	
	@PostMapping("/checkout")
	@CheckUserSession
	public ResponseEntity<ApiResponse<OrderDTO>> createOrder(@RequestBody List<OrderItemDTO> items, HttpSession session) {
		CustomerDTO customerDTO = (CustomerDTO) session.getAttribute("customerDTO");
		OrderDTO orderDTO = orderService.saveOrder(customerDTO.getId(), items);
		return ResponseEntity.ok(ApiResponse.success("新增成功", orderDTO));
	}
}


