package com.ydn.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ydn.project.model.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{

}
