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

import java.util.ArrayList;
import java.util.List;
@Configuration
public class DepartmentMapper {
    private final BranchRepository branchRepository;

    public DepartmentMapper(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    public List<DepartmentDTO> toDTOS(List<Department> departments) {
        List<DepartmentDTO> departmentDTOList = new ArrayList<>();
        for (Department department : departments) {
            DepartmentDTO departmentDto = new DepartmentDTO();
            departmentDto.setId(department.getId());
            departmentDto.setName(department.getName());
            departmentDto.setBranchId(department.getBranch() != null ? department.getBranch().getId() : null);
            departmentDto.setStatus(String.valueOf(department.getStatus()));
            departmentDTOList.add(departmentDto);
        }
        return departmentDTOList;
    }

    public Department toEntity(DepartmentDTO departmentDTO) {
        Department department = new Department();
        department.setId(department.getId());
        department.setName(departmentDTO.getName());
        department.setBranch(branchRepository.findById(departmentDTO.getBranchId()).orElseThrow(() -> new EntityNotFoundException("branch topilmadi")));
        department.setStatus(CommonStatus.valueOf(departmentDTO.getStatus()));
        return department;
    }
}
