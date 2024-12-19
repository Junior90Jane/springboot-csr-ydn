package com.ydn.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ydn.project.model.entity.Order;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	// 取得該使用者的訂單紀錄
	List<Order> findByCustomerId(Long customerId);
}
