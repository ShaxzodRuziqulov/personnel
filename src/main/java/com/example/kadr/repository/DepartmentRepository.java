/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:15:05
 */
package com.example.kadr.repository;

import com.example.kadr.entity.Department;
import com.example.kadr.entity.enumitation.hr.CommonStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findByBranchId(Long id);

    List<Department> findByBranchIdOrderByIdAsc(Long branchId);

    List<Department> findByStatusOrderByName(CommonStatus status);

    @Query("select count(d) from Department d where d.status=:status")
    Long countActiveDepartment(@Param("status") CommonStatus status);

    @Modifying
    @Query("update Department a set a.status = :commonStatus where a.id = :id")
    void updateStatus(@Param("id") Long id, @Param("commonStatus") CommonStatus commonStatus);

}
