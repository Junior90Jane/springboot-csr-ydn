package com.ydn.project.service;

import java.util.List;

import com.ydn.project.model.dto.OrderDTO;
import com.ydn.project.model.dto.OrderItemDTO;


public interface OrderService {
	
	// 根據使用者 id 取得該使用者的訂購資料
	public List<OrderDTO> findOrdersByCustomerId(Long customerId);
	
	// 新增訂購單
	public OrderDTO saveOrder(Long id, List<OrderItemDTO> items);
	
}
