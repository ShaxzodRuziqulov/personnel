/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:16:17
 */
package com.example.kadr.service;

import com.example.kadr.entity.Department;
import com.example.kadr.service.dto.DepartmentDTO;

import java.util.List;

public interface DepartmentService {
    String create(DepartmentDTO departmentDTO);

    String update(DepartmentDTO departmentDTO);

    List<DepartmentDTO> all();

    void delete(Long id);

    Department findById(Long id);
    List<Department> findByBranchId(Long id);
}
