package com.ydn.project.model.entity;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.DialectOverride.GeneratedColumn;


import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
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

@Entity
@Table(name = "customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable = false)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String phoneNumber;
	
	@OneToMany(mappedBy = "customer")
	private List<Order> orders;
	
	// 建立用戶可以關注商品的多對多關係
	@ManyToMany()
	@JoinTable(
		name = "customer_product", // 關聯表名稱
		joinColumns = @JoinColumn(name = "customer_id"), // 用戶外鍵
		inverseJoinColumns = @JoinColumn(name = "product_id") // 商品外鍵
	)
	private Set<Product> favoriteProducts;
	
	/*
     * hashCode 方法中出現了遞迴循環，通常是因為 User 和 Product 實體之間的雙向關聯造成的，Hibernate 無法處理這種循環依賴。
     * 所以要自行實現
     * */
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
	
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false, length = 10)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "age_group", nullable = false, length = 20)
    private AgeGroup ageGroup;
    
    public enum Gender {
        MALE("男"),
        FEMALE("女");

        private final String displayName;

        Gender(String displayName) {
            this.displayName = displayName;
        }
    }
    
    public enum AgeGroup {
        AGE_18_25("18-25歲"),
        AGE_26_35("26-35歲"),
        AGE_36_45("36-45歲"),
        AGE_46_PLUS("46歲以上");

        private final String displayName;

        AgeGroup(String displayName) {
            this.displayName = displayName;
        }
    }
}
