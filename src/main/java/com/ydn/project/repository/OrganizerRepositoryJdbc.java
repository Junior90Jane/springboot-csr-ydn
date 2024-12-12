package com.ydn.project.repository;

import java.util.List;
import java.util.Optional;

import com.ydn.project.model.entity.Customer;
import com.ydn.project.model.entity.Organizer;

public interface OrganizerRepositoryJdbc {
	
	int update(Organizer organizer);

}
