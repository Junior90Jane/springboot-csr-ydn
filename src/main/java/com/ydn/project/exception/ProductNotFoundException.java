package com.ydn.project.exception;

// 自定義無此商品例外
public class ProductNotFoundException extends RuntimeException {
	
	public ProductNotFoundException() {
		super();
	}
	
	public ProductNotFoundException(String message) {
		super(message);
	}
}
