/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:19:29
 */
package com.example.kadr.service.impl;

import com.example.kadr.entity.Job;
import com.example.kadr.entity.enumitation.hr.CommonStatus;
import com.example.kadr.repository.EmployeeRepository;
import com.example.kadr.entity.Employee;
import com.example.kadr.repository.JobRepository;
import com.example.kadr.service.EmployeeService;
import com.example.kadr.service.JobService;
import com.example.kadr.service.dto.EmployeeDTO;
import com.example.kadr.service.mapper.EmployeeMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final EntityManager entityManager;
    private final JobRepository jobRepository;
    private final JobService jobService;


    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper, EntityManager entityManager, JobRepository jobRepository, JobService jobService) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.entityManager = entityManager;
        this.jobRepository = jobRepository;
        this.jobService = jobService;
    }

    @Override
    public String create(EmployeeDTO employeeDTO) {
        try {
            employeeRepository.save(employeeMapper.toEntity(employeeDTO));
            return "Muvaffaqiyatli saqlandi";
        } catch (Exception e) {
            e.printStackTrace();
            return "Xatolik";
        }
    }

    @Override
    public String update(EmployeeDTO employeeDTO) {
        try {
            employeeRepository.save(employeeMapper.toEntity(employeeDTO));
            return "Malumotlar uzgartirildi";
        } catch (Exception e) {
            e.printStackTrace();
            return "Xatolik";
        }
    }

    @Override
    public List<EmployeeDTO> all() {
        return employeeMapper.toDTOs(employeeRepository.findAll());
    }

    @Override
    public void delete(Long id) {
        employeeRepository.updateStatus(id, CommonStatus.DELETED);
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElseGet(Employee::new);
    }

    public EmployeeDTO getEmployeeDTOByHrId(Long hrId) {
        EmployeeDTO employeeDTO = null;
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("hr.get_one_employee_by_hr_id");
        query.registerStoredProcedureParameter(1, Class.class, ParameterMode.REF_CURSOR);
        query.registerStoredProcedureParameter(2, Long.class, ParameterMode.IN);
        query.setParameter(2, hrId);
        query.execute();
        return null;
    }

    public List<EmployeeDTO> findAllByStatus(CommonStatus status) {
        return employeeMapper.toDTOs(employeeRepository.findAllByStatus(status));
    }

    public List<EmployeeDTO> findAllFreeEmployee(Long jobId) {
        Job job = jobService.findById(jobId);
        if (job != null) {
            Long structureId = job.getDepartment().getBranch().getStructure().getId();
            List<Employee> employees = employeeRepository.findAllFreeEmployee(structureId,CommonStatus.ACTIVE);
            return employeeMapper.toDTOs(employees);
        }
        return null;
    }
}
