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
import com.example.kadr.service.BranchService;
import com.example.kadr.service.DepartmentService;
import com.example.kadr.service.dto.BranchDepartmentList;
import com.example.kadr.service.dto.DepartmentDTO;
import com.example.kadr.service.mapper.DepartmentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final Logger log = LoggerFactory.getLogger(DepartmentService.class);
    private final DepartmentRepository departmentRepository;
    private final BranchRepository branchRepository;
    private final DepartmentMapper departmentMapper;
    private final BranchService branchService;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, BranchRepository branchRepository, DepartmentMapper departmentMapper, BranchService branchService) {
        this.departmentRepository = departmentRepository;
        this.branchRepository = branchRepository;
        this.departmentMapper = departmentMapper;
        this.branchService = branchService;
    }

    @Override
    public String create(DepartmentDTO departmentDTO) {
        try {
            departmentRepository.save(departmentMapper.toEntity(departmentDTO));
            return "Muvaffaqiyatli saqlandi";
        } catch (Exception e) {
            e.printStackTrace();
            return "Xatolik";
        }
    }

    @Override
    public String update(DepartmentDTO departmentDTO) {
        try {
            departmentRepository.save(departmentMapper.toEntity(departmentDTO));
            return "Muvaffaqiyatli uzgartirildi";
        } catch (Exception e) {
            e.printStackTrace();
            return "Xatolik";
        }
    }

    @Override
    public List<DepartmentDTO> all() {
        return departmentMapper.toDTOS(departmentRepository.findAll());
    }

    public Department findById(Long id) {
        return departmentRepository.findById(id).orElseGet(Department::new);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Department : {}", id);
        branchRepository.updateStatus(id, CommonStatus.DELETED);
    }
    public List<DepartmentDTO> findDepartmentByBranchId(Long id){
        List<Department> department = departmentRepository.findByBranchId(id);
        return departmentMapper.toDTOS(department);
    }
    public BranchDepartmentList findAllBranchAndDepartmentByBranchId(Long branchId){
        BranchDepartmentList branchDepartmentList = new BranchDepartmentList();
        branchDepartmentList.setBranchDTOList(branchService.findAllByParentId(branchId));
        branchDepartmentList.setDepartmentDTOList(findDepartmentByBranchId(branchId));
        return branchDepartmentList;
    }

}
