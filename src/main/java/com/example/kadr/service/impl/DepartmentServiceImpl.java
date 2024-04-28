/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:16:20
 */
package com.example.kadr.service.impl;

import com.example.kadr.entity.enumitation.hr.CommonStatus;
import com.example.kadr.repository.BranchRepository;
import com.example.kadr.repository.DepartmentRepository;
import com.example.kadr.entity.Department;
import com.example.kadr.service.DepartmentService;
import com.example.kadr.service.dto.DepartmentDTO;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final Logger log = LoggerFactory.getLogger(DepartmentService.class);
    private final DepartmentRepository departmentRepository;
    private final BranchRepository branchRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, BranchRepository branchRepository) {
        this.departmentRepository = departmentRepository;
        this.branchRepository = branchRepository;
    }

    @Override
    public String create(DepartmentDTO departmentDTO) {
        try {
            departmentRepository.save(toEntity(departmentDTO));
            return "Muvaffaqiyatli saqlandi";
        } catch (Exception e) {
            e.printStackTrace();
            return "Xatolik";
        }
    }

    @Override
    public String update(DepartmentDTO departmentDTO) {
        try {
            departmentRepository.save(toEntity(departmentDTO));
            return "Muvaffaqiyatli uzgartirildi";
        } catch (Exception e) {
            e.printStackTrace();
            return "Xatolik";
        }
    }

    @Override
    public List<DepartmentDTO> all() {
        return toDTOS(departmentRepository.findAll());
    }

    public Department findById(Long id) {
        return departmentRepository.findById(id).orElseGet(Department::new);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Department : {}", id);
        branchRepository.updateStatus(id, CommonStatus.DELETED);
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
