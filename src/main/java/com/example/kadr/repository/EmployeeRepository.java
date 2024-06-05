/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:15:08
 */
package com.example.kadr.repository;

import com.example.kadr.entity.Employee;
import com.example.kadr.entity.enumitation.hr.CommonStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Transactional
    @Modifying
    @Query("update Employee a set a.status = :commonStatus where a.id = :id")
    void updateStatus(@Param("id") Long id, @Param("commonStatus") CommonStatus commonStatus);

    @Query("select e from Employee e " +
            "left join e.job j " +
            "left join j.department d " +
            "left join d.branch s " +
            "where e.status = :status")
    List<Employee> findAllByStatus(@Param("status") CommonStatus status);

    @Query(value = "select e from Employee e " +
            "left join Job j on j.id = e.job.id " +
            "left join Department d on d.id = j.department.id " +
            "left join Branch b on b.id= d.branch.id " +
            "where e.status =:status and (:structureId is null or b.structure.id = :structureId)",
            nativeQuery = true)
    List<Employee> findAllFreeEmployee(@Param("structureId") Long structureId, @Param("status") CommonStatus status);

}
