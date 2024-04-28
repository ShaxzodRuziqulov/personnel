/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:19:29
 */
package com.example.kadr.service.impl;

import com.example.kadr.entity.enumitation.hr.CommonStatus;
import com.example.kadr.repository.EmployeeRepository;
import com.example.kadr.repository.JobRepository;
import com.example.kadr.entity.Employee;
import com.example.kadr.service.EmployeeService;
import com.example.kadr.service.dto.EmployeeDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final JobRepository jobRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, JobRepository jobRepository) {
        this.employeeRepository = employeeRepository;
        this.jobRepository = jobRepository;
    }

    @Override
    public String create(EmployeeDTO employeeDTO) {
        try {
            employeeRepository.save(toEntity(employeeDTO));
            return "Muvaffaqiyatli saqlandi";
        } catch (Exception e) {
            e.printStackTrace();
            return "Xatolik";
        }
    }

    @Override
    public String update(EmployeeDTO employeeDTO) {
        try {
            employeeRepository.save(toEntity(employeeDTO));
            return "Malumotlar uzgartirildi";
        } catch (Exception e) {
            e.printStackTrace();
            return "Xatolik";
        }
    }

    @Override
    public List<EmployeeDTO> all() {
        return toDTOs(employeeRepository.findAll());
    }

    @Override
    public void delete(Long id) {
        employeeRepository.updateStatus(id,CommonStatus.DELETED);
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElseGet(Employee::new);
    }

    public List<EmployeeDTO> toDTOs(List<Employee> employees) {
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (Employee employee : employees) {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setFirstName(employee.getFirstName());
            employeeDTO.setLastName(employee.getLastName());
            employeeDTO.setDateBirth(employee.getDateBirth());
            employeeDTO.setStatus(String.valueOf(employee.getStatus()));
            employeeDTO.setJobId(employee.getJob().getId());
            employeeDTO.setUsername(employee.getUserName());
            employeeDTO.setGender(employee.getGender());
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
