package com.sipl.vehicle.management.vehicleDTO;

import com.sipl.vehicle.management.entity.AuditEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RfidTagMasterDto {
    private Integer id;
    private String rfidTagId;
    private TagTypeMasterDto tagTypeMasterDto;
    private String rfidTag;
    private String tagTypeName;
    private Boolean isActive;
    private Integer start;
    private Integer end;
    private AuditEntity auditEntity;
}
