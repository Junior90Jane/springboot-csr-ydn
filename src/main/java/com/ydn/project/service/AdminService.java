package com.ydn.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ydn.project.model.dto.AdminDto;

import jakarta.persistence.Column;


public interface AdminService {
	public List<AdminDto> getAllAdmins();
	public AdminDto getAdminById(Long adId);
	public void addAdmin(AdminDto adminDto);
	public void addAdmin(Long adId, String adAccount, String adPassword);

}
