/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:15:08
 */
package com.example.kadr.repository;

import com.example.kadr.entity.Job;
import com.example.kadr.entity.enumitation.hr.CommonStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByDepartmentId(Long id);

    @Modifying
    @Query("update Job a set a.status = :commonStatus where a.id = :id")
    void updateStatus(@Param("id") Long id, @Param("commonStatus") CommonStatus commonStatus);

    List<Job> findByStatusOrderByName(CommonStatus status);

    @Query("select j.position.id from Job j where j.id = :id")
    Long findByPositionIdOrderById(@Param("id") Long jobId);



    @Query("select j from Job j where j.sortOrder>:sortOrder")
    List<Job> findBySortOrderGreaterThan(@Param("sortOrder") Long sortOrder);

    @Query("select j from Job j where j.name=:name")
    List<Job> findByName(@Param("name") String name);

    @Query("select j from Job j where j.position.id=:positionId")
    List<Job> findByPositionId(@Param("positionId") Long positionId);

    @Query("select j from Job j where j.department.id=:departmentId and j.status=:status")
    List<Job> findByDepartmentIdAndStatus(@Param("departmentId") Long departmentId, @Param("status") CommonStatus status);

    @Query("select j from Job j where j.position.id = :positionId and j.sortOrder > :sortOrder")
    List<Job> findByPositionIdAndSortOrderGreaterThan(@Param("positionId") Long positionId, @Param("sortOrder") Long sortOrder);

    @Query("select j from Job j order by j.sortOrder asc")
    List<Job> findAllOrderBySortOrderAsc();

    @Query("select j from Job j where j.name=:name and j.department.id=:departmentId")
    List<Job> findByNameAndDepartmentId(@Param("name") String name, @Param("departmentId") Long departmentId);
}
