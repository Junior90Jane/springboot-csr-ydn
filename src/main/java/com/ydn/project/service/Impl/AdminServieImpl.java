package com.ydn.project.service.Impl;

import java.util.List;
import static java.util.stream.Collectors.toList;
import java.util.Optional;
import java.util.stream.Collector;

import org.hibernate.annotations.Collate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ydn.project.exception.admin.AdminAlreadyExistsException;
import com.ydn.project.exception.admin.AdminException;
import com.ydn.project.mapper.AdminMapper;
import com.ydn.project.model.dto.AdminDto;
import com.ydn.project.model.entity.Admin;
import com.ydn.project.repository.AdminRepository;
import com.ydn.project.repository.AdminRepositoryJdbc;
import com.ydn.project.service.AdminService;

@Service
public class AdminServieImpl implements AdminService{

	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private AdminRepositoryJdbc adminRepositoryJdbc;
	
	
	 /**
     * 新增管理員
     */
    @Override
    public void addAdmin(Admin admin) {
        // 檢查是否已存在相同帳號
        Optional<Admin> optAdmin = adminRepository.findByAdAccount(admin.getAdAccount());
        if (optAdmin.isPresent()) {
            throw new AdminAlreadyExistsException("新增失敗: 帳號 " + admin.getAdAccount() + " 已存在");
        }

        // 儲存資料，資料庫自動生成 adId
        Admin savedAdmin = adminRepository.save(admin);
        if (savedAdmin.getAdId() == null) {
            throw new AdminException("無法新增! 自增 ID 未生成");
        }
    }

    /**
     * 使用帳號與密碼新增管理員
     */
    @Override
    public void addAdmin(Long adId, String adAccount, String adPassword) {
        Admin admin = new Admin();
        admin.setAdAccount(adAccount);
        admin.setAdPassword(adPassword);
        addAdmin(admin);
    }


	@Override
	public List<AdminDto> getAllAdmins() {
		return adminRepositoryJdbc.findAll()
				.stream()
				.map(adminMapper::toDTO)
				.collect(toList());
	}

	@Override
	public AdminDto getAdminById(Long adId) {
		Admin admin = adminRepositoryJdbc.findById(adId)
				.orElseThrow(() -> new AdminException("找不到此管理員: ID: " + adId));
		return adminMapper.toDTO(admin);
	}




}
