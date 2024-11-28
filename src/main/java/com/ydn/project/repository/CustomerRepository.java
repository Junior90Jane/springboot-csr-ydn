package com.ydn.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ydn.project.model.entity.Admin;
import com.ydn.project.model.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	Optional<Customer> findByCtAccount(String ctAccount);
}
