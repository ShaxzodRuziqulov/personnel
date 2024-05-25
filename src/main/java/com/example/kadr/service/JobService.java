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
import jakarta.transaction.Transactional;

import java.util.List;

public interface JobService {
    String create(JobDTO jobDTO);
    String update(JobDTO jobDTO);
    List<JobDTO> all ();
    @Transactional
    void delete(Long id);
    Job findById(Long id);
    List<JobDTO> findAllJobByDepartmentId(Long id);
    DepartmentJobList findAllDepartmentByJobId(Long id);
}
