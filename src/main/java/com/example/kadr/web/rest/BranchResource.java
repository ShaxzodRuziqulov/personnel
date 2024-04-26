/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:22:16
 */
package com.example.kadr.web.rest;

import com.example.kadr.entity.Branch;
import com.example.kadr.entity.Region;
import com.example.kadr.service.BranchService;
import com.example.kadr.service.dto.BranchDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branch")
public class BranchResource {
    private final BranchService branchService;

    public BranchResource(BranchService branchService) {
        this.branchService = branchService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody BranchDTO branchDTO) {
        String response = branchService.create(branchDTO);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody BranchDTO branchDTO) {
        String response = branchService.update(branchDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<?> all() {
        List<BranchDTO> branches = branchService.all();
        return ResponseEntity.ok(branches);
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<?> byId(@PathVariable Long id) {
        Branch branch = branchService.findById(id);
        return ResponseEntity.ok(branch);
    }

    @GetMapping("/all-by/parentId")
    public ResponseEntity<?> parentId(@PathVariable Long parentId) {
        List<Branch> branch = branchService.findAllByParentId(parentId);
        return ResponseEntity.ok(branch);
    }

    @GetMapping("/by-region/{regionId}")
    public ResponseEntity<?> findByRegionId(@PathVariable Long regionId) {
        List<Branch> branch = branchService.findByRegionId(regionId);
        return ResponseEntity.ok(branch);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        branchService.delete(id);
        return ResponseEntity.ok("o'chirildi");
    }
}