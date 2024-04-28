/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:17:23
 */
package com.example.kadr.service.impl;

import com.example.kadr.entity.enumitation.hr.CommonStatus;
import com.example.kadr.repository.DepartmentRepository;
import com.example.kadr.repository.JobRepository;
import com.example.kadr.repository.PositionRepository;
import com.example.kadr.entity.Job;
import com.example.kadr.service.JobService;
import com.example.kadr.service.dto.JobDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final PositionRepository positionRepository;
    private final DepartmentRepository departmentRepository;

    public JobServiceImpl(JobRepository jobRepository, PositionRepository positionRepository, DepartmentRepository departmentRepository) {
        this.jobRepository = jobRepository;
        this.positionRepository = positionRepository;
        this.departmentRepository = departmentRepository;
    }


    @Override
    public String create(JobDTO jobDTO) {
        try {
            jobRepository.save(toEntity(jobDTO));
            return "Muvaffaqiyatli saqlandi";
        } catch (Exception e) {
            e.printStackTrace();
            return "Xatolik";
        }
    }

    @Override
    public String update(JobDTO jobDTO) {
        try {
            jobRepository.save(toEntity(jobDTO));
            return "Muvaffaqiyatli uzgartirildi";
        }catch (Exception e){
            e.printStackTrace();
            return "Xatolik";
        }
    }

    public List<JobDTO> toDTOS(List<Job> jobs) {
        List<JobDTO> jobDTOList = new ArrayList<>();
        for (Job job : jobs) {
            JobDTO jobDTO = new JobDTO();
            jobDTO.setId(job.getId());
            jobDTO.setName(job.getName());
            jobDTO.setDepartmentId(job.getDepartment() != null ? job.getDepartment().getId() : null);
            jobDTO.setPositionId(job.getPosition() != null ? job.getPosition().getId() : null);
            jobDTO.setStatus(String.valueOf(job.getStatus()));
            jobDTOList.add(jobDTO);
        }
        return jobDTOList;
    }

    @Override
    public List<JobDTO> all() {
        return toDTOS(jobRepository.findAll());
    }

    @Override
    public void delete(Long id) {
        jobRepository.updateStatus(id, CommonStatus.DELETED);
    }

    public Job findById(Long id) {
        return jobRepository.findById(id).orElseGet(Job::new);
    }
    public List<Job> findByDepartmentId(Long id) {
        return jobRepository.findByDepartmentId(id);
    }

    public Job toEntity(JobDTO jobDTO) {
        Job job = new Job();
        job.setId(job.getId());
        job.setName(jobDTO.getName());
        job.setDepartment(departmentRepository.findById(jobDTO.getDepartmentId()).orElseThrow(() -> new EntityNotFoundException("Department topilmadi")));
        job.setPosition(positionRepository.findById(jobDTO.getPositionId()).orElseThrow(() -> new EntityNotFoundException("Position topilmadi")));
        job.setStatus(CommonStatus.valueOf(jobDTO.getStatus()));
        return job;
    }

}
