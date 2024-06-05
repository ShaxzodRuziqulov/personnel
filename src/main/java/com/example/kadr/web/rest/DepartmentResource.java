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
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DepartmentResource {
    private final DepartmentService departmentService;

    public DepartmentResource(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/departments/create")
    public ResponseEntity<?> create(@RequestBody DepartmentDTO departmentDTO) throws URISyntaxException {
        DepartmentDTO result = departmentService.create(departmentDTO);
        return ResponseEntity
                .created(new URI("api/departments/create" + result.getId()))
                .body(result);
    }

    @PutMapping("/departments/update/{id}")
    public ResponseEntity<?> update(@RequestBody DepartmentDTO departmentDTO, @PathVariable Long id) {
        if (departmentDTO.getId() == null || !departmentDTO.getId().equals(id)) {
            return ResponseEntity.badRequest().body("Invalid ID");
        }
        try {
            DepartmentDTO result = departmentService.update(departmentDTO);
            return ResponseEntity
                    .ok().body(result);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/departments/all-list/by-sort-order")
    public ResponseEntity<?> findAllByOrderBySortOrderDesc() {
        List<DepartmentDTO> departments = departmentService.findAllByOrderBySortOrderDesc();
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/departments/by/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Department department = departmentService.findById(id);
        return ResponseEntity.ok(department);
    }

    @GetMapping("/departments/by-branch/{branchId}")
    public ResponseEntity<?> findByBranchId(@PathVariable Long branchId) {
        List<DepartmentDTO> department = departmentService.findDepartmentByBranchId(branchId);
        return ResponseEntity.ok(department);
    }

    @GetMapping("/departments/list-by/{branchId}")
    public ResponseEntity<?> findAllDepartmentBranchIdList(@PathVariable Long branchId) {
        List<DepartmentDTO> department = departmentService.findAllDepartmentByBranchIdList(branchId);
        return ResponseEntity.ok(department);
    }

    @GetMapping("/departments/by-branchAndDepartment/list/{branchId}")
    public ResponseEntity<?> findAllBranchAndDepartment(@PathVariable Long branchId) {
        BranchDepartmentList department = departmentService.findAllBranchAndDepartmentByBranchId(branchId);
        return ResponseEntity.ok(department);
    }

    @DeleteMapping("/departments/delete/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.ok("o'chirildi");
    }

    @PutMapping("/departments/active/{id}")
    public ResponseEntity<?> activeDepartment(@PathVariable Long id) {
        departmentService.activeDepartment(id);
        return ResponseEntity.ok("Active qilindi");
    }

    @PutMapping("/departments/inactive/{id}")
    public ResponseEntity<?> deActiveDepartment(@PathVariable Long id) {
        departmentService.inActiveDepartment(id);
        return ResponseEntity.ok("InActive qilindi");
    }

    @GetMapping("/departments/by-status/active")
    private ResponseEntity<?> findByStatusActive() {
        List<DepartmentDTO> findByStatusActive = departmentService.findByStatusActive();
        return ResponseEntity.ok(findByStatusActive);
    }

    @GetMapping("/departments/by-status/inactive")
    private ResponseEntity<?> findByStatusInActive() {
        List<DepartmentDTO> findByStatusInActive = departmentService.findByStatusInActive();
        return ResponseEntity.ok(findByStatusInActive);
    }

    @GetMapping("/departments/by-status/active/count")
    private ResponseEntity<?> countByDepartmentStatus() {
        Long countByDepartmentStatus = departmentService.countByDepartmentStatus();
        return ResponseEntity.ok(countByDepartmentStatus);
    }
}
