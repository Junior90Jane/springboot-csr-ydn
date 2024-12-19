package com.ydn.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ydn.project.model.entity.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
