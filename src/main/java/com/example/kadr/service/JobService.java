/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:17:22
 */
package com.example.kadr.service;

import com.example.kadr.entity.Job;
import com.example.kadr.service.dto.DepartmentJobList;
import com.example.kadr.service.dto.JobDTO;

import java.util.List;

public interface JobService {
    String create(JobDTO jobDTO);

    String update(JobDTO jobDTO);

    List<JobDTO> all();

    void delete(Long id);

    List<JobDTO> findByStatusInactive();

    List<JobDTO> findByStatusActive();

    Job findById(Long id);

    List<JobDTO> findAllJobByDepartmentId(Long id);

    DepartmentJobList findAllDepartmentByJobId(Long id);

    Long findByPositionIdOrderById(Long jobId);

    List<JobDTO> findBySortOrderGreaterThan(Long sortOrder);

    List<JobDTO> findByName(String name);

    List<JobDTO> findByPositionId(Long positionId);

    List<JobDTO> findByDepartmentIdAndStatus(Long departmentId);

    List<JobDTO> findByPositionIdAndSortOrderGreaterThan(Long positionId, Long sortOrder);

    List<JobDTO> findAllOrderBySortOrderAsc();

    List<JobDTO> findByNameAndDepartmentId(String name, Long departmentId);
}
