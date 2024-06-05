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
import jakarta.transaction.Transactional;
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
    public DepartmentDTO create(DepartmentDTO departmentDTO) {

        Department department = departmentMapper.toEntity(departmentDTO);
        department = departmentRepository.save(department);
        return departmentMapper.toDTO(department);
    }

    @Override
    public DepartmentDTO update(DepartmentDTO departmentDTO) {
        Department department = new Department();
        department = departmentRepository.save(department);
        return departmentMapper.toDTO(department);
    }

    @Override
    public List<DepartmentDTO> findAllByOrderBySortOrderDesc() {
        return departmentMapper.toDTOS(departmentRepository.findAllByOrderBySortOrderDesc());
    }

    public Department findById(Long id) {
        return departmentRepository.findById(id).orElseGet(Department::new);
    }

    @Transactional
    @Override
    public void deleteDepartment(Long id) {
        departmentRepository.updateStatus(id, CommonStatus.DELETED);
    }

    @Transactional
    public void activeDepartment(Long id) {
        departmentRepository.updateStatus(id, CommonStatus.ACTIVE);
    }

    @Transactional
    public void inActiveDepartment(Long id) {
        departmentRepository.updateStatus(id, CommonStatus.INACTIVE);
    }

    public List<DepartmentDTO> findDepartmentByBranchId(Long id) {
        List<Department> department = departmentRepository.findByBranchId(id);
        return departmentMapper.toDTOS(department);
    }

    public List<DepartmentDTO> findAllDepartmentByBranchIdList(Long id) {
        List<Department> department = departmentRepository.findByBranchIdOrderByIdAsc(id);
        return departmentMapper.toDTOS(department);
    }

    public BranchDepartmentList findAllBranchAndDepartmentByBranchId(Long branchId) {
        BranchDepartmentList branchDepartmentList = new BranchDepartmentList();
        branchDepartmentList.setBranchDTOList(branchService.findAllByParentId(branchId));
        branchDepartmentList.setDepartmentDTOList(findDepartmentByBranchId(branchId));
        return branchDepartmentList;
    }

    @Override
    public List<DepartmentDTO> findByStatusActive() {
        return departmentMapper.toDTOS(departmentRepository
                .findByStatusOrderByName(CommonStatus.ACTIVE));
    }

    @Override
    public List<DepartmentDTO> findByStatusInActive() {
        return departmentMapper.toDTOS(departmentRepository
                .findByStatusOrderByName(CommonStatus.INACTIVE));
    }

    @Override
    public Long countByDepartmentStatus() {
        return departmentRepository.countActiveDepartment(CommonStatus.ACTIVE);
    }

}
