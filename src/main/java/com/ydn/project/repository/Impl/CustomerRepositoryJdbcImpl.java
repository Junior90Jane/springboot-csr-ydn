package com.ydn.project.repository.Impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ydn.project.model.dto.CustomerDto;
import com.ydn.project.model.entity.AdminAccount;
import com.ydn.project.model.entity.Customer;
import com.ydn.project.repository.CustomerRepositoryJdbc;

@Repository
@PropertySource("classpath:sql.properties") // 自動到 src/main/respurces 找到 sql.properties
public class CustomerRepositoryJdbcImpl implements CustomerRepositoryJdbc{

	
	private static final Logger logger = LoggerFactory.getLogger(CustomerRepositoryJdbcImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Value("${customers.sql.update}")
	private String updateSql;
	

	@Override
	public int update(Customer customers) {
		return jdbcTemplate.update(updateSql, customers.getPasswordHash(), customers.getAgeGroup(), customers.getEmail(), customers.getPhoneNumber());
	}


}
