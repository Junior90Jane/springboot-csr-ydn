package com.ydn.project.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ydn.project.model.dto.OrganizerDto;
import com.ydn.project.model.entity.Organizer;

@Component
public class OrganizerMapper {
	@Autowired
	private ModelMapper modelMapper;
	
	public Organizer toEntity(OrganizerDto organizerDto) {
		return modelMapper.map(organizerDto, Organizer.class);
	} 
	
	public OrganizerDto toDto(Organizer organizer) {
		return modelMapper.map(organizer, OrganizerDto.class);
	}

}
