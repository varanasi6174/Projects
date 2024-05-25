package com.sipl.tracker_rest_repo.dao.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "products")
@Entity
public class ProductMaster {

	private static final long serialVersionUID = 3980494633996944406L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @Column(name = "product_name")
    private String productName;
    
    @Column(name = "is_active")
    private Boolean isActive;
    
    @Embedded
	private AuditEntity auditEntity;
    
}
