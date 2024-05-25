package com.sipl.vehicle.management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sipl.vehicle.management.entity.RfidTagMaster;

@Repository
public interface RfidTagMasterRepository extends JpaRepository<RfidTagMaster, Integer> {
    @Query("from RfidTagMaster tm where tm.rfidTagId=?1 and tm.isActive=true")
    Optional<RfidTagMaster> getRfidMasterBasedOnRfidTagNum(String tagNum);

    @Query("from RfidTagMaster tm where (tm.tagTypeMaster.id=?1 and tm.rfidTagId like %?2%) and tm.isActive=true ORDER BY tm.id DESC")
    Page<RfidTagMaster> findPageByTypeAndTagId(Integer id, String tagid, Pageable page);

    @Query("from RfidTagMaster tm where (tm.tagTypeMaster.id=?1 or tm.rfidTagId like %?2%) and tm.isActive=true ORDER BY tm.id DESC")
    Page<RfidTagMaster> findPageByTypeOrTagId(Integer id, String tagid, Pageable page);

    @Query("from RfidTagMaster tm where tm.rfidTagId=?1 and tm.isActive=true")
    RfidTagMaster findByRfidTagId(String tagNum);

    @Query("from RfidTagMaster tm ORDER BY tm.id DESC")
    Page<RfidTagMaster> findAll(Pageable page);

    @Query("from RfidTagMaster tm where tm.isActive = true ORDER BY tm.id DESC")
    Page<RfidTagMaster> findActiveTags(PageRequest pageRequest);

    @Query("from RfidTagMaster tm where tm.rfidTagId=?1")
    List<RfidTagMaster> findByTagId(String rfidTagId);
}

