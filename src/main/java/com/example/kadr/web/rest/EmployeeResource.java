/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:22:32
 */
package com.example.kadr.web.rest;

import com.example.kadr.entity.Employee;
import com.example.kadr.entity.enumitation.hr.CommonStatus;
import com.example.kadr.service.EmployeeService;
import com.example.kadr.service.dto.EmployeeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeResource {
    private final EmployeeService employeeService;

    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody EmployeeDTO employeeDTO) {
        String response = employeeService.create(employeeDTO);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody EmployeeDTO employeeDTO) {
        String response = employeeService.update(employeeDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<?> all() {
        List<EmployeeDTO> employees = employeeService.all();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Employee employee = employeeService.findById(id);
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.ok("o'chirildi");
    }

    @GetMapping("/allByStatus/{status}")
    public ResponseEntity<?> findAllByStatus(@PathVariable CommonStatus status) {
        List<EmployeeDTO> findAllByStatus = employeeService.findAllByStatus(status);
        return ResponseEntity.ok(findAllByStatus);
    }

    @GetMapping("free-employee-list/by-job/{jobId}")
    public ResponseEntity<?> findAllFreeEmployee(@PathVariable Long jobId) {
        List<EmployeeDTO> findAllFreeEmployee = employeeService.findAllFreeEmployee(jobId);
        return ResponseEntity.ok(findAllFreeEmployee);
    }
}
