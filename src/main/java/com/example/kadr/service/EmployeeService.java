/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:19:27
 */
package com.example.kadr.service;

import com.example.kadr.entity.Employee;
import com.example.kadr.entity.enumitation.hr.CommonStatus;
import com.example.kadr.service.dto.EmployeeDTO;
import jakarta.transaction.Transactional;

import java.util.List;

public interface EmployeeService {
    EmployeeDTO create(EmployeeDTO employeeDTO);
    EmployeeDTO update(EmployeeDTO employeeDTO);
    List<EmployeeDTO> all ();
    void delete(Long id);
    Employee findById(Long id);
    List<EmployeeDTO> findAllByStatus(CommonStatus status);
}
