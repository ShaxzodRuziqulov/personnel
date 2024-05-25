/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:22:22
 */
package com.example.kadr.web.rest;

import com.example.kadr.entity.Department;
import com.example.kadr.service.DepartmentService;
import com.example.kadr.service.dto.BranchDepartmentList;
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
        List<DepartmentDTO> department = departmentService.findDepartmentByBranchId(branchId);
        return ResponseEntity.ok(department);
    }

    @GetMapping("/by-branch/list/{branchId}")
    public ResponseEntity<?> findAllBranchAndDepartment(@PathVariable Long branchId) {
        BranchDepartmentList department = departmentService.findAllBranchAndDepartmentByBranchId(branchId);
        return ResponseEntity.ok(department);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.ok("o'chirildi");
    }
    @PutMapping("/active/{id}")
    public ResponseEntity<?> activeDepartment(@PathVariable Long id) {
        departmentService.activeDepartment(id);
        return ResponseEntity.ok("Active qilindi");
    }
    @PutMapping("/inactive/{id}")
    public ResponseEntity<?> deActiveDepartment(@PathVariable Long id) {
        departmentService.inActiveDepartment(id);
        return ResponseEntity.ok("InActive qilindi");
    }

    @GetMapping("/by-status/active")
    private ResponseEntity<?> findByStatusActive() {
        List<DepartmentDTO> findByStatusActive = departmentService.findByStatusActive();
        return ResponseEntity.ok(findByStatusActive);
    }
    @GetMapping("/by-status/inactive")
    private ResponseEntity<?> findByStatusInActive() {
        List<DepartmentDTO> findByStatusInActive = departmentService.findByStatusInActive();
        return ResponseEntity.ok(findByStatusInActive);
    }
    @GetMapping("/by-status/active/count")
    private ResponseEntity<?> countByDepartmentStatus() {
        Long countByDepartmentStatus = departmentService.countByDepartmentStatus();
        return ResponseEntity.ok(countByDepartmentStatus);
    }
}
