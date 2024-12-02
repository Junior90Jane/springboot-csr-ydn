package com.ydn.project.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ydn.project.exception.customer.CustomerAlreadyExistsException;
import com.ydn.project.exception.customer.CustomerException;
import com.ydn.project.exception.organizer.OrganizerAlreadyExistsException;
import com.ydn.project.exception.organizer.OrganizerException;
import com.ydn.project.mapper.OrganizerMapper;
import com.ydn.project.model.dto.OrganizerDto;
import com.ydn.project.model.entity.Customer;
import com.ydn.project.model.entity.Organizer;
import com.ydn.project.repository.OrganizerRepository;
import com.ydn.project.service.OrganizerService;

@Service
public class OrganizerServiceImpl implements OrganizerService{
	
	@Autowired
	private OrganizerMapper organizerMapper;
	@Autowired
	private OrganizerRepository organizerRepository;

	@Override
	public List<OrganizerDto> getAllOrganizers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrganizerDto getOrganizerByAccount(String ogAccount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addOrganizer(Organizer organizer) {
		Optional<Organizer> optOrganizer = organizerRepository.findByOgAccount(organizer.getOgAccount());
		if(optOrganizer.isPresent()) {
			throw new OrganizerAlreadyExistsException("新增失敗: 帳號 " + organizer.getOgAccount() + " 已存在");
		}
		Organizer savaedOrganizer = organizerRepository.save(organizer);
        if (savaedOrganizer.getOgAccount() == null) {
            throw new OrganizerException("無法新增! 自增 ID 未生成");
        }
	}

	@Override
	public void addOrganizer(String ogAccount, String ogPassword, String company, String ogEmail, String ogPhone) {
		Organizer organizer = new Organizer();
		organizer.setOgAccount(ogAccount);
		organizer.setOgPassword(ogPassword);
		organizer.setCompany(company);
		organizer.setOgEmail(ogEmail);
		organizer.setOgPhone(ogPhone);
		addOrganizer(organizer);
	}

}
