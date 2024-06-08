/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:15:08
 */
package com.example.kadr.repository;

import com.example.kadr.entity.Employee;
import com.example.kadr.entity.enumitation.hr.CommonStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Modifying
    @Query("update Employee a set a.status = :commonStatus where a.id = :id")
    void updateStatus(@Param("id") Long id, @Param("commonStatus") CommonStatus commonStatus);

    @Query("select e from Employee e " +
            "left join e.job j " +
            "left join j.department d " +
            "left join d.branch s " +
            "where e.status = :status")
    List<Employee> findAllByStatus(@Param("status") CommonStatus status);

}
