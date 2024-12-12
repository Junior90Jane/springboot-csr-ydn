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

import com.ydn.project.model.entity.Customer;
import com.ydn.project.model.entity.Organizer;
import com.ydn.project.repository.OrganizerRepositoryJdbc;

@Repository
@PropertySource("classpath:sql.properties")
public class OrganizerRepositoryJdbcImpl implements OrganizerRepositoryJdbc{
	
	private static final Logger logger = LoggerFactory.getLogger(OrganizerRepositoryJdbcImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Value("${organizers.sql.update}")
	private String updateSql;
	
	

	@Override
	public int update(Organizer organizer) {
		return jdbcTemplate.update(updateSql, organizer.getPasswordHash(), organizer.getEmail(), organizer.getPhoneNumber());
	}


}
