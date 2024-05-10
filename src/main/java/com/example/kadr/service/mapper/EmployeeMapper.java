/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:06.05.2024
 * TIME:21:02
 */
package com.example.kadr.service.mapper;

import com.example.kadr.entity.Employee;
import com.example.kadr.entity.enumitation.hr.CommonStatus;
import com.example.kadr.repository.JobRepository;
import com.example.kadr.service.dto.EmployeeDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeMapper {
    private final JobRepository jobRepository;

    public EmployeeMapper(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public List<EmployeeDTO> toDTOs(List<Employee> employees) {
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (Employee employee : employees) {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setId(employee.getId());
            employeeDTO.setFirstName(employee.getFirstName());
            employeeDTO.setLastName(employee.getLastName());
            employeeDTO.setDateBirth(employee.getDateBirth());
            employeeDTO.setStatus(String.valueOf(employee.getStatus()));
            employeeDTO.setJobId(employee.getJob().getId());
            employeeDTO.setUsername(employee.getUserName());
            employeeDTO.setGender(employee.getGender());
            employeeDTO.setHashId(employee.getHashId());
            employeeDTOS.add(employeeDTO);
        }
        return employeeDTOS;
    }

    public Employee toEntity(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setDateBirth(employeeDTO.getDateBirth());
        employee.setStatus(CommonStatus.valueOf(employeeDTO.getStatus()));
        employee.setJob(jobRepository.findById(employeeDTO.getJobId()).orElseThrow(() -> new EntityNotFoundException("Job topilmadi")));
        employee.setUserName(employeeDTO.getUsername());
        employee.setGender(employeeDTO.getGender());
        employee.setHashId(employeeDTO.getHashId());
        return employee;
    }
}
