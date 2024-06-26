/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:24.04.2024
 * TIME:22:46
 */
package com.example.kadr.repository;

import com.example.kadr.entity.Region;
import com.example.kadr.entity.enumitation.hr.CommonStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionRepository extends JpaRepository<Region,Long> {

    List<Region> findAllByOrderBySortOrderDesc();

    @Modifying
    @Query("update Region a set a.status = :commonStatus where a.id = :id")
    void updateStatus(@Param("id") Long id, @Param("commonStatus") CommonStatus commonStatus);
}
