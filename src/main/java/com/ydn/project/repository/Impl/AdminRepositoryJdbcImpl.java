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
import com.ydn.project.repository.AdminRepositoryJdbc;

@Repository
@PropertySource("classpath:sql.properties") // 自動到 src/main/respurces 找到 sql.properties
public class AdminRepositoryJdbcImpl implements AdminRepositoryJdbc{

	private static final Logger logger = LoggerFactory.getLogger(AdminRepositoryJdbcImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Value("${admins.sql.findAll}")
	private String findAllSql;
	
	@Value("${admins.sql.findById}")
	private String findByIdSql;
	
	@Value("${admins.sql.save}")
	private String saveSql;
	
	@Value("${admins.sql.update}")
	private String updateSql;
	
	@Value("${admins.sql.deleteById}")
	private String deleteByIdSql;
	
	
	@Override
	public List<Admin> findAll() {
		return jdbcTemplate.query(findAllSql, new BeanPropertyRowMapper<>(Admin.class));
	}

	@Override
	public Optional<Admin> findById(Long adId) {
		// 因為 queryForObject 若沒有找到資料會自動拋出例外, 所以要 try-catch 保護
		try {
			Admin admin = jdbcTemplate.queryForObject(findByIdSql, new BeanPropertyRowMapper<>(Admin.class), adId);
			return Optional.of(admin);
		} catch (Exception e) {
			logger.info(e.toString());
		}
		return Optional.empty();
	}

	@Override
	public int save(Admin admin) {
		return jdbcTemplate.update(saveSql, admin.getAdId(), admin.getAdAccount(), admin.getAdPassword());
	}

	@Override
	public int update(Admin admin) {
		return jdbcTemplate.update(updateSql, admin.getAdId(), admin.getAdAccount(), admin.getAdPassword());
	}

	@Override
	public int deleteById(Long adId) {
		return jdbcTemplate.update(deleteByIdSql, adId);
	}

}
