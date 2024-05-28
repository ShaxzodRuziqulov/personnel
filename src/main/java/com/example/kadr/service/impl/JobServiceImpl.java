/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:17:23
 */
package com.example.kadr.service.impl;

import com.example.kadr.entity.enumitation.hr.CommonStatus;
import com.example.kadr.repository.JobRepository;
import com.example.kadr.entity.Job;
import com.example.kadr.service.DepartmentService;
import com.example.kadr.service.JobService;
import com.example.kadr.service.dto.DepartmentJobList;
import com.example.kadr.service.dto.JobDTO;
import com.example.kadr.service.mapper.JobMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final JobMapper jobMapper;
    private final DepartmentService departmentService;

    public JobServiceImpl(JobRepository jobRepository, JobMapper jobMapper, DepartmentService departmentService) {
        this.jobRepository = jobRepository;
        this.jobMapper = jobMapper;
        this.departmentService = departmentService;
    }

    @Override
    public String create(JobDTO jobDTO) {
        try {
            if (jobDTO.getStatus() == null) {
                jobDTO.setStatus(String.valueOf(CommonStatus.ACTIVE));
            }
            jobRepository.save(jobMapper.toEntity(jobDTO));
            return "Muvaffaqiyatli saqlandi";
        } catch (Exception e) {
            e.printStackTrace();
            return "Xatolik";
        }
    }

    @Override
    public String update(JobDTO jobDTO) {
        try {
            jobRepository.save(jobMapper.toEntity(jobDTO));
            return "Muvaffaqiyatli uzgartirildi";
        } catch (Exception e) {
            e.printStackTrace();
            return "Xatolik";
        }
    }

    @Override
    public List<JobDTO> all() {
        return jobMapper.toDTOS(jobRepository.findAll());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        jobRepository.updateStatus(id, CommonStatus.DELETED);
    }

    public List<JobDTO> findByStatusInactive() {
        return jobMapper.toDTOS(
                jobRepository.findByStatusOrderByName(
                        CommonStatus.INACTIVE));
    }

    public List<JobDTO> findByStatusActive() {
        return jobMapper.toDTOS(
                jobRepository.findByStatusOrderByName(
                        CommonStatus.ACTIVE));
    }

    public Job findById(Long id) {
        return jobRepository.findById(id).orElseGet(Job::new);
    }

    public List<JobDTO> findAllJobByDepartmentId(Long id) {
        List<Job> job = jobRepository.findByDepartmentId(id);
        return jobMapper.toDTOS(job);
    }

    public DepartmentJobList findAllDepartmentByJobId(Long id) {
        DepartmentJobList departmentJobList = new DepartmentJobList();
        departmentJobList.setDepartmentDTOList(departmentService.findDepartmentByBranchId(id));
        departmentJobList.setJobDTOList(findAllJobByDepartmentId(id));
        return departmentJobList;
    }

    public Long findByPositionIdOrderById(Long jobId) {
        return jobRepository.findByPositionIdOrderById(jobId);
    }

    public List<JobDTO> findBySortOrderGreaterThan(Long sortOrder) {
        return jobMapper.toDTOS(jobRepository.findBySortOrderGreaterThan(sortOrder));
    }

    public List<JobDTO> findByName(String name) {
        return jobMapper.toDTOS(jobRepository.findByName(name));
    }

    public List<JobDTO> findByPositionId(Long positionId) {
        return jobMapper.toDTOS(jobRepository.findByPositionId(positionId));
    }

    public List<JobDTO> findByDepartmentIdAndStatus(Long departmentId) {
        return jobMapper.toDTOS(jobRepository.findByDepartmentIdAndStatus(departmentId, CommonStatus.ACTIVE));
    }

    public List<JobDTO> findByPositionIdAndSortOrderGreaterThan(Long positionId, Long sortOrder) {
        return jobMapper.toDTOS(jobRepository.findByPositionIdAndSortOrderGreaterThan(positionId, sortOrder));
    }

    public List<JobDTO> findAllOrderBySortOrderAsc() {
        return jobMapper.toDTOS(jobRepository.findAllOrderBySortOrderAsc());
    }

    public List<JobDTO> findByNameAndDepartmentId(String name, Long departmentId) {
        return jobMapper.toDTOS(jobRepository.findByNameAndDepartmentId(name, departmentId));
    }
}
