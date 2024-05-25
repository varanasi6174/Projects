package com.sipl.vehicle.management.vehicleDTO;

import com.sipl.vehicle.management.entity.AuditEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TagTypeMasterDto {
    private Integer id;
    private String tagTypeName;
    private Boolean isActive;
    private AuditEntity auditEntity;  
}