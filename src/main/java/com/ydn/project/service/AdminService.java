package com.ydn.project.service;

import org.springframework.stereotype.Service;

import com.ydn.project.model.dto.AdminDto;

import jakarta.persistence.Column;


@Service
public interface AdminService {
	
	public void addAdmin(AdminDto adminDto);
	public void addAdmin(Long adId, String adAccount, String adPassword);

}
