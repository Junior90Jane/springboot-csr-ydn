package com.ydn.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ydn.project.model.entity.Customer;
import com.ydn.project.model.entity.Organizer;

@Repository
public interface OrganizerRepository extends JpaRepository<Organizer, Long>{
	
	Optional<Organizer> findByUsername(String username);
	int deleteByUsername(String username);

}
