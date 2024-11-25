package com.ydn.project.repository;

import java.util.List;
import java.util.Optional;

import com.ydn.project.model.entity.Admin;

public interface AdminRepositoryJdbc {
	
	List<Admin> findAll();
	Optional<Admin> findById(Long adId);
	int save(Admin admin); 
	int update(Admin admin);
	int deleteById(Long adId);

}
