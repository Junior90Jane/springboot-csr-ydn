package com.ydn.project.model.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
	private Long id;
	private Integer quantity;
	private ProductDTO product;
}
