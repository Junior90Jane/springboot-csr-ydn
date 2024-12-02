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
	
	private static final Logger logger = LoggerFactory.getLogger(AdminRepositoryJdbcImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Value("${organizers.sql.findAll}")
	private String findAllSql;
	@Value("${organizers.sql.findByAccount}")
	private String findByAccountSql;
	@Value("${organizers.sql.save}")
	private String saveSql;
	@Value("${organizers.sql.update}")
	private String updateSql;
	@Value("${organizers.sql.deleteByAccount}")
	private String deleteByAccountSql;
	
	
	
	@Override
	public List<Organizer> findAll() {
		return jdbcTemplate.query(findAllSql, new BeanPropertyRowMapper<>(Organizer.class));
	}

	@Override
	public Optional<Organizer> findByAccount(String ogAccount) {
		try {
			Organizer organizer = jdbcTemplate.queryForObject(findByAccountSql, new BeanPropertyRowMapper<>(Organizer.class), ogAccount);
			return Optional.of(organizer);
		} catch (Exception e) {
			logger.info(e.toString());
		}
		return Optional.empty();
	}
	
	@Override
	public int save(Organizer organizer) {
		return jdbcTemplate.update(saveSql, organizer.getOgAccount(), organizer.getOgPassword(), organizer.getCompany(), organizer.getOgEmail(), organizer.getOgPhone());
	}

	@Override
	public int update(Organizer organizer) {
		return jdbcTemplate.update(updateSql, organizer.getOgPassword(), organizer.getOgEmail(), organizer.getOgPhone());
	}

	@Override
	public int deleteByAccount(String ogAccount) {
		return jdbcTemplate.update(deleteByAccountSql, ogAccount);
	}

}
