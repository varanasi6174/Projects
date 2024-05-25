package com.sipl.tracker_rest_repo.dto;

import com.sipl.tracker_rest_repo.dao.entities.AuditEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StateDto {
    private Integer stateId;
    private String stateName;
    private Boolean isActive;
    private Boolean isDeleted;
    private AuditEntity auditEntity;
}
