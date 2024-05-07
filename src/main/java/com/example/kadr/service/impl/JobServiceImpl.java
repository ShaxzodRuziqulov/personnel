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
    public void delete(Long id) {
        jobRepository.updateStatus(id, CommonStatus.DELETED);
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


}
