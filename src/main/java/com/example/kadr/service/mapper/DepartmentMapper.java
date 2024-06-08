/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:06.05.2024
 * TIME:20:22
 */
package com.example.kadr.service.mapper;

import com.example.kadr.entity.Department;
import com.example.kadr.entity.enumitation.hr.CommonStatus;
import com.example.kadr.repository.BranchRepository;
import com.example.kadr.service.dto.DepartmentDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DepartmentMapper {
    private final BranchRepository branchRepository;

    public DepartmentMapper(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    public List<DepartmentDTO> toDTOS(List<Department> departments) {
        return departments.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Department toEntity(DepartmentDTO departmentDTO) {
        Department department = new Department();
        department.setId(departmentDTO.getId());
        department.setSortOrder(department.getSortOrder());
        department.setName(departmentDTO.getName());
        department.setBranch(branchRepository.findById(departmentDTO.getBranchId()).orElseThrow(() -> new EntityNotFoundException("branch topilmadi")));
        department.setStatus(CommonStatus.valueOf(departmentDTO.getStatus()));
        return department;
    }

    public DepartmentDTO toDTO(Department department) {
        if (department == null) {
            return null;
        }
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(department.getId());
        departmentDTO.setName(department.getName());
        departmentDTO.setSortOrder(department.getSortOrder());
        departmentDTO.setBranchId(department.getBranch().getId());
        departmentDTO.setStatus(String.valueOf(department.getStatus()));
        return departmentDTO;
    }
}
