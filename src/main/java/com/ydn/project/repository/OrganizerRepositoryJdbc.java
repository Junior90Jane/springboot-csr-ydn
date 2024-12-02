package com.ydn.project.repository;

import java.util.List;
import java.util.Optional;

import com.ydn.project.model.entity.Customer;
import com.ydn.project.model.entity.Organizer;

public interface OrganizerRepositoryJdbc {
	List<Organizer> findAll();
	Optional<Organizer> findByAccount(String ogAccount);
	int save(Organizer organizer); 
	int update(Organizer organizer);
	int deleteByAccount(String ogAccount);

}
