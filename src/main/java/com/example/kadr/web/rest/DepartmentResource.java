/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:22:22
 */
package com.example.kadr.web.rest;

import com.example.kadr.entity.Department;
import com.example.kadr.service.DepartmentService;
import com.example.kadr.service.dto.DepartmentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
public class DepartmentResource {
    private final DepartmentService departmentService;

    public DepartmentResource(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DepartmentDTO departmentDTO) {
        String response = departmentService.create(departmentDTO);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody DepartmentDTO departmentDTO) {
        String response = departmentService.update(departmentDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<?> all() {
        List<DepartmentDTO> departments = departmentService.all();
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Department department = departmentService.findById(id);
        return ResponseEntity.ok(department);
    }

    @GetMapping("/by-branch/{branchId}")
    public ResponseEntity<?> findByBranchId(@PathVariable Long branchId) {
        List<DepartmentDTO> department = departmentService.findByBranchId(branchId);
        return ResponseEntity.ok(department);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        departmentService.delete(id);
        return ResponseEntity.ok("o'chirildi");
    }
}
