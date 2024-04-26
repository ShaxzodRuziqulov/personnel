/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:16:20
 */
package com.example.kadr.service.impl;

import com.example.kadr.repository.BranchRepository;
import com.example.kadr.repository.DepartmentRepository;
import com.example.kadr.entity.Department;
import com.example.kadr.service.DepartmentService;
import com.example.kadr.service.dto.DepartmentDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final BranchRepository branchRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, BranchRepository branchRepository) {
        this.departmentRepository = departmentRepository;
        this.branchRepository = branchRepository;
    }

    @Override
    public String create(DepartmentDTO departmentDTO) {
        try {
            Department department = new Department();
            department.setId(departmentDTO.getId());
            department.setName(departmentDTO.getName());
            department.setBranch(branchRepository.findById(departmentDTO.getBranchId()).orElseThrow(() -> new EntityNotFoundException("branch topilmadi")));
            departmentRepository.save(department);
            return "Muvaffaqiyatli saqlandi";
        } catch (Exception e) {
            e.printStackTrace();
            return "Xatolik";
        }
    }

    @Override
    public String update(DepartmentDTO departmentDTO) {
        try {
            if (departmentDTO.getId() != null) {
                if (departmentRepository.findById(departmentDTO.getId()).isPresent()) {
                    Department department = departmentRepository.findById(departmentDTO.getId()).get();
                    department.setName(departmentDTO.getName());
                    department.setBranch(branchRepository.findById(departmentDTO.getBranchId()).orElseThrow(() -> new EntityNotFoundException("branch topilmadi")));
                    departmentRepository.save(department);
                    return "Muvaffaqiyatli uzgartirildi";
                } else {
                    return " Id topilmadi";
                }
            } else {
                return "Xatolik: id null ga teng";
            }
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
        Department delete = departmentRepository.getReferenceById(id);
        departmentRepository.delete(delete);
    }

    public List<DepartmentDTO> toDTOS(List<Department> departments) {
        List<DepartmentDTO> departmentDTOList = new ArrayList<>();
        for (Department department : departments) {
            DepartmentDTO departmentDto = new DepartmentDTO();
            departmentDto.setId(department.getId());
            departmentDto.setName(department.getName());
            departmentDto.setBranchId(department.getBranch() != null ? department.getBranch().getId() : null);
            departmentDTOList.add(departmentDto);
        }
        return departmentDTOList;
    }
}
