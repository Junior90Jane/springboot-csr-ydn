package com.ydn.project.service;

import java.util.List;

import com.ydn.project.model.dto.CustomerDto;
import com.ydn.project.model.dto.OrganizerDto;
import com.ydn.project.model.entity.Customer;
import com.ydn.project.model.entity.Organizer;

import jakarta.persistence.Column;

public interface OrganizerService {
	public List<OrganizerDto> getAllOrganizers();
	public OrganizerDto getOrganizerByAccount(String ogAccount);
	public void addOrganizer(Organizer organizer);
	public void addOrganizer(String ogAccount, String ogPassword, String company, String ogEmail, String ogPhone);

}
