package com.ydn.project.model.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false)
    private String passwordHash;
    private String salt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AgeGroup ageGroup;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 15)
    private String phoneNumber;
    
    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    public enum Gender {
        M, F
    }

    public enum AgeGroup {
        AGE_18_25, AGE_26_35, AGE_36_45, AGE_46_PLUS
    }
    
	// 建立顧客可以關注商品的多對多關係
	@ManyToMany()
	@JoinTable(
		name = "user_product", // 關聯表名稱
		joinColumns = @JoinColumn(name = "user_id"), // 用戶外鍵
		inverseJoinColumns = @JoinColumn(name = "product_id") // 商品外鍵
	)
	private Set<ProgramApplication> favoriteProgramApplications;

}
