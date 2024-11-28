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

import com.ydn.project.model.entity.Admin;
import com.ydn.project.model.entity.Customer;
import com.ydn.project.repository.CustomerRepositoryJdbc;

@Repository
@PropertySource("classpath:sql.properties") // 自動到 src/main/respurces 找到 sql.properties
public class CustomerRepositoryJdbcImpl implements CustomerRepositoryJdbc{

	
	private static final Logger logger = LoggerFactory.getLogger(AdminRepositoryJdbcImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Value("${customers.sql.findAll}")
	private String findAllSql;
	@Value("${customers.sql.findByAccount}")
	private String findByAccountSql;
	@Value("${customers.sql.save}")
	private String saveSql;
	@Value("${customers.sql.update}")
	private String updateSql;
	@Value("${customers.sql.deleteByAccount}")
	private String deleteByAccountSql;
	
	@Override
	public List<Customer> findAll() {
		return jdbcTemplate.query(findAllSql, new BeanPropertyRowMapper<>(Customer.class));
	}

	@Override
	public Optional<Customer> findByAccount(String adAccount) {// 因為 queryForObject 若沒有找到資料會自動拋出例外, 所以要 try-catch 保護
		try {
			Customer customer = jdbcTemplate.queryForObject(findByAccountSql, new BeanPropertyRowMapper<>(Customer.class), adAccount);
			return Optional.of(customer);
		} catch (Exception e) {
			logger.info(e.toString());
		}
		return Optional.empty();
	}

	@Override
	public int save(Customer customers) {
		return jdbcTemplate.update(saveSql, customers.getCtAccount(), customers.getCtPassword(), customers.getGender(), customers.getAge(), customers.getCtEmail(), customers.getCtPhone());
	}

	@Override
	public int update(Customer customers) {
		return jdbcTemplate.update(updateSql, customers.getCtPassword(), customers.getAge(), customers.getCtEmail(), customers.getCtPhone());
	}

	@Override
	public int deleteByAccount(String adAccount) {
		return jdbcTemplate.update(deleteByAccountSql, adAccount);
	}

}
