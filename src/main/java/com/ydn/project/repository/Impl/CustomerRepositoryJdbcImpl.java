package com.ydn.project.repository.Impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import com.ydn.project.model.entity.Customer;
import com.ydn.project.repository.CustomerRepositoryJdbc;

@Repository
@PropertySource("classpath:sql.properties") // 自動到 src/main/respurces 找到 sql.properties
public class CustomerRepositoryJdbcImpl implements CustomerRepositoryJdbc{

	
	private static final Logger logger = LoggerFactory.getLogger(AdminRepositoryJdbcImpl.class);
	
	@Override
	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Customer> findById(Long adId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public int save(Customer Customer) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Customer Customer) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(Long adId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
