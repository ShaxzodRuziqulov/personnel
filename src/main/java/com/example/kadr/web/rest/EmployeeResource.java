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
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeResource {
    private final EmployeeService employeeService;

    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employees/create")
    public ResponseEntity<?> create(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO result = employeeService.create(employeeDTO);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/employees/update/{id}")
    public ResponseEntity<?> update(@RequestBody EmployeeDTO employeeDTO, @PathVariable Long id) {
        if (employeeDTO.getId() == null || !employeeDTO.getId().equals(id)) {
            return ResponseEntity.badRequest().body("Invalid Id");
        }
        try {
            EmployeeDTO result = employeeService.update(employeeDTO);
            return ResponseEntity.ok(result);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/employees/all")
    public ResponseEntity<?> all() {
        List<EmployeeDTO> employees = employeeService.all();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/employees/by/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Employee employee = employeeService.findById(id);
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/employees/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/employees/by-status/{status}")
    public ResponseEntity<?> findAllByStatus(@PathVariable CommonStatus status) {
        List<EmployeeDTO> findAllByStatus = employeeService.findAllByStatus(status);
        return ResponseEntity.ok(findAllByStatus);
    }

}
