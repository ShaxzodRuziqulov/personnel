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

@Component
public class JobMapper {
    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;

    public JobMapper(DepartmentRepository departmentRepository, PositionRepository positionRepository) {
        this.departmentRepository = departmentRepository;
        this.positionRepository = positionRepository;
    }

    public List<JobDTO> toDTOS(List<Job> jobs) {
        List<JobDTO> jobDTOList = new ArrayList<>();
        for (Job job : jobs) {
            JobDTO jobDTO = new JobDTO();
            jobDTO.setId(job.getId());
            jobDTO.setSortOrder(job.getSortOrder());
            jobDTO.setName(job.getName());
            jobDTO.setDepartmentId(job.getDepartment() != null ? job.getDepartment().getId() : null);
            jobDTO.setPositionId(job.getPosition() != null ? job.getPosition().getId() : null);
            jobDTO.setStatus(String.valueOf(job.getStatus()));
            jobDTOList.add(jobDTO);
        }
        return jobDTOList;
    }
    public Job toEntity(JobDTO jobDTO) {
        Job job = new Job();
        job.setId(job.getId());
        job.setSortOrder(job.getSortOrder());
        job.setName(jobDTO.getName());
        job.setDepartment(departmentRepository.findById(jobDTO.getDepartmentId()).orElseThrow(() -> new EntityNotFoundException("Department topilmadi")));
        job.setPosition(positionRepository.findById(jobDTO.getPositionId()).orElseThrow(() -> new EntityNotFoundException("Position topilmadi")));
        job.setStatus(CommonStatus.valueOf(jobDTO.getStatus()));
        return job;
    }
}
