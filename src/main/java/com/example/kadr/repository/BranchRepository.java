/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:15:07
 */
package com.example.kadr.repository;

import com.example.kadr.entity.Branch;
import com.example.kadr.entity.enumitation.hr.CommonStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {

    List<Branch> findByParentId(Long parentId);

    List<Branch> findByStructureId(Long structureId);

    List<Branch> findByRegionId(Long id);

    List<Branch> findByStatusOrderByName(CommonStatus status);

    @Query("select count (b) from Branch b where b.status =:status")
    Long countStatusBranches(@Param("status") CommonStatus status);

    @Modifying
    @Query("update Branch a set a.status = :commonStatus where a.id = :id")
    void updateStatus(@Param("id") Long id, @Param("commonStatus") CommonStatus commonStatus);

    @Query("select coalesce(max (b.sortOrder),0 ) from Branch b where b.parent.id=:parentId")
    Long getMaxIdByParentId(Long parentId);

    @Query("select coalesce(max (b.sortOrder), 0) from Branch b where b.parent.id is null")
    Long getMaxIdByParentIdIsNull(Long parentId);

}
