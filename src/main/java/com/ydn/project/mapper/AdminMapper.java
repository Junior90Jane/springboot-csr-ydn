package com.ydn.project.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ydn.project.model.dto.AdminDto;
import com.ydn.project.model.entity.Admin;

@Component
public class AdminMapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
	// admin Dto 轉 Entity
	public Admin toEntity(AdminDto adminDto) {
		return modelMapper.map(adminDto, Admin.class);
	}
	
	// admin Entity 轉 Dto
	public AdminDto toDTO(Admin admin) {
		return modelMapper.map(admin, AdminDto.class);
	}
	
	

}
