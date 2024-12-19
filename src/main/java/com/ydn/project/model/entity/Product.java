package com.ydn.project.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @Column(name = "event_name", nullable = false, length = 100)
    private String productName;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type", nullable = false, length = 20)
    private EventType productType;

    @Column(name = "available_tickets", nullable = false)
    private Integer availableTickets;

    @Column(name = "ticket_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal ticketPrice;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

	// 修改 imageBase64 欄位的註解為長文本
	@Column(columnDefinition = "LONGTEXT")
	private String imageBase64;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "application_status", nullable = false, length = 20)
    private ApplicationStatus applicationStatus;

    @Column(name = "application_time")
    private LocalDateTime applicationTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer_id", nullable = false)
    private Organizer organizer;
    
    public enum EventType {
        EXHIBITION("展覽"),
        LECTURE("講座"),
        MUSIC("音樂");

        private final String displayName;

        EventType(String displayName) {
            this.displayName = displayName;
        }
    }
    
    public enum ApplicationStatus {
        PENDING("待審核"),
        APPROVED("已通過"),
        REJECTED("已拒絕");

        private final String displayName;

        ApplicationStatus(String displayName) {
            this.displayName = displayName;
        }
    }
    
    // 與 ProductImage 的一對一關聯 (單向)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_image_id")
    private ProductImage productImage;
	
    @ManyToMany(mappedBy = "favoriteProducts")
    private Set<Customer> favoriteUsers;
}

