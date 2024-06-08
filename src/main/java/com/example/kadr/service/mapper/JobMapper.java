/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:06.05.2024
 * TIME:20:56
 */
package com.example.kadr.service.mapper;

import com.example.kadr.entity.Job;
import com.example.kadr.entity.enumitation.hr.CommonStatus;
import com.example.kadr.repository.DepartmentRepository;
import com.example.kadr.repository.PositionRepository;
import com.example.kadr.service.dto.JobDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JobMapper {
    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;

    public JobMapper(DepartmentRepository departmentRepository, PositionRepository positionRepository) {
        this.departmentRepository = departmentRepository;
        this.positionRepository = positionRepository;
    }

    public List<JobDTO> toDTOS(List<Job> jobs) {
        return jobs.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Job toEntity(JobDTO jobDTO) {
        Job job = new Job();
        job.setId(jobDTO.getId());
        job.setSortOrder(jobDTO.getSortOrder());
        job.setName(jobDTO.getName());
        job.setDepartment(departmentRepository.findById(jobDTO.getDepartmentId()).orElseThrow(() -> new EntityNotFoundException("Department topilmadi")));
        job.setPosition(positionRepository.findById(jobDTO.getPositionId()).orElseThrow(() -> new EntityNotFoundException("Position topilmadi")));
        job.setStatus(CommonStatus.valueOf(jobDTO.getStatus()));
        return job;
    }

    public JobDTO toDTO(Job job) {
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setName(job.getName());
        jobDTO.setStatus(String.valueOf(job.getStatus()));
        jobDTO.setSortOrder(job.getSortOrder());
        jobDTO.setDepartmentId(job.getDepartment().getId());
        jobDTO.setPositionId(job.getPosition().getId());
        return jobDTO;
    }


}
