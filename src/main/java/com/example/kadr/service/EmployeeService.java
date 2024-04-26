/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:19:27
 */
package com.example.kadr.service;

import com.example.kadr.entity.Employee;
import com.example.kadr.entity.Job;
import com.example.kadr.entity.request.ReqEmployee;
import com.example.kadr.entity.request.ReqJob;
import com.example.kadr.service.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    String create(EmployeeDTO employeeDTO);
    String update(EmployeeDTO employeeDTO);
    List<EmployeeDTO> all ();
    void delete(Long id);
    Employee findById(Long id);
}
