package com.ydn.project.repository;

import java.util.List;
import java.util.Optional;

import com.ydn.project.model.entity.Customer;

public interface CustomerRepositoryJdbc {
	int update(Customer customers);
}
