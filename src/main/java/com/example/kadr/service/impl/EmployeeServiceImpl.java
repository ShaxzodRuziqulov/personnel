/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:19:29
 */
package com.example.kadr.service.impl;

import com.example.kadr.repository.EmployeeRepository;
import com.example.kadr.repository.JobRepository;
import com.example.kadr.entity.Employee;
import com.example.kadr.entity.enumitation.EmployeeStatus;
import com.example.kadr.entity.enumitation.Gender;
import com.example.kadr.entity.request.ReqEmployee;
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
            Employee employee = new Employee();
            employee.setFirstName(employeeDTO.getFirstName());
            employee.setLastName(employeeDTO.getLastName());
            employee.setDateBirth(employeeDTO.getDateBirth());
            employee.setStatus(EmployeeStatus.FREE);
            employee.setJob(jobRepository.findById(employeeDTO.getJobId()).orElseThrow(() -> new EntityNotFoundException("Job topilmadi")));
            employee.setUserName(employeeDTO.getUsername());
            employee.setGender(employeeDTO.getGender());
            employee.setHashId(employeeDTO.getHashId());
            employeeRepository.save(employee);
            return "Muvaffaqiyatli saqlandi";
        } catch (Exception e) {
            e.printStackTrace();
            return "Xatolik";
        }
    }

    @Override
    public String update(EmployeeDTO employeeDTO) {
        try {
            if (employeeDTO.getId() != null) {
                if (employeeRepository.findById(employeeDTO.getId()).isPresent()) {
                    Employee employee = employeeRepository.findById(employeeDTO.getId()).get();
                    employee.setFirstName(employeeDTO.getFirstName());
                    employee.setLastName(employeeDTO.getLastName());
                    employee.setDateBirth(employeeDTO.getDateBirth());
                    employee.setStatus(employeeDTO.getStatus());
                    employee.setJob(jobRepository.findById(employeeDTO.getJobId()).orElseThrow(() -> new EntityNotFoundException("Job topilmadi")));
                    employee.setUserName(employeeDTO.getUsername());
                    employee.setGender(employeeDTO.getGender());
                    employee.setHashId(employeeDTO.getHashId());
                    return "Malumotlar uzgartirildi";
                } else {
                    return "Id topilmadi";
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
    public List<EmployeeDTO> all() {
        return toDTOs(employeeRepository.findAll());
    }

    @Override
    public void delete(Long id) {
        Employee employee = employeeRepository.getReferenceById(id);
        employeeRepository.delete(employee);
    }
    public Employee findById(Long id){
        return employeeRepository.findById(id).orElseGet(Employee::new);
    }
    public List<EmployeeDTO> toDTOs(List<Employee> employees){
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (Employee e : employees) {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setFirstName(e.getFirstName());
            employeeDTO.setLastName(e.getLastName());
            employeeDTO.setDateBirth(e.getDateBirth());
            employeeDTO.setStatus(e.getStatus());
            employeeDTO.setJobId(e.getJob().getId());
            employeeDTO.setUsername(e.getUserName());
            employeeDTO.setGender(e.getGender());
            employeeDTOS.add(employeeDTO);
        }
        return employeeDTOS;
    }
}
