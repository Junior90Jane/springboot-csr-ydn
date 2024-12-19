package com.ydn.project.service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ydn.project.model.dto.OrderDTO;
import com.ydn.project.model.dto.OrderItemDTO;
import com.ydn.project.model.entity.Customer;
import com.ydn.project.model.entity.Order;
import com.ydn.project.model.entity.OrderItem;
import com.ydn.project.repository.CustomerRepository;
import com.ydn.project.repository.OrderItemRepository;
import com.ydn.project.repository.OrderRepository;
import com.ydn.project.repository.ProductRepository;
import com.ydn.project.service.OrderService;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

@Service
@Component
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<OrderDTO> findOrdersByCustomerId(Long customerId) {
		return orderRepository.findByCustomerId(customerId).stream() // ... Order
				.map(order -> modelMapper.map(order, OrderDTO.class)) // ... OrderDTO
				.collect(Collectors.toList());
	}

	@Override
	public OrderDTO saveOrder(Long id, List<OrderItemDTO> items) {
		// 1. 得到 user
		Optional<Customer> optCustomer = customerRepository.findById(id);
		if(optCustomer.isEmpty()) return null;
		
		// 2. 建立訂單 + 設定關聯關係
		Order order = new Order();
		order.setCustomer(optCustomer.get()); // 設定與 user 的關聯關係
		
		// 3. 儲存 order
		//orderRepository.save(order); // 非聯集操作時要加入
		
		// 4. 建立訂單項目列表 (非聯集操作時要加入)
//		items.forEach(item -> {
//			OrderItem orderItem = modelMapper.map(item, OrderItem.class);
//			orderItem.setOrder(order); // 設定與 order 的關聯關係
//			orderItemRepository.save(orderItem); // 儲存
//		});
		
		// 3. 建立訂單項目列表 (聯集操作時要加入 @OneToMany(cascade = CascadeType.ALL))
		List<OrderItem> orderItems = items.stream()
				.map(item -> {
					OrderItem orderItem = modelMapper.map(item, OrderItem.class);
					orderItem.setOrder(order); // 設定與 order 的關聯關係
					return orderItem;
				}).collect(Collectors.toList());
		
		// 4. order 設定與 orderItems 關聯關係 + 儲存 (聯集操作時要加入 @OneToMany(cascade = CascadeType.ALL))
		order.setItems(orderItems);
		orderRepository.save(order); 
		
		return modelMapper.map(order, OrderDTO.class);
	}
	
}
