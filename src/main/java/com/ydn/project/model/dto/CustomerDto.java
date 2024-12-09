package com.ydn.project.model.dto;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.ydn.project.model.entity.Customer.AgeGroup;
import com.ydn.project.model.entity.Customer.Gender;
import com.ydn.project.model.entity.Order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

	@Id
    private Long customerId;
    private String username;
    private AgeGroup ageGroup;
    private String email;
    private String phoneNumber;

}
