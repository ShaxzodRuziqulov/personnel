/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:22:16
 */
package com.example.kadr.web.rest;

import com.example.kadr.entity.Branch;
import com.example.kadr.service.BranchService;
import com.example.kadr.service.dto.BranchDTO;
import com.example.kadr.service.dto.StructureBranchList;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BranchResource {
    private final BranchService branchService;

    public BranchResource(BranchService branchService) {
        this.branchService = branchService;
    }

    @PostMapping("/branches/create")
    public ResponseEntity<?> create(@RequestBody BranchDTO branchDTO) throws URISyntaxException {
        BranchDTO result = branchService.create(branchDTO);
        return ResponseEntity
                .created(new URI("api/branches/create" + result.getId()))
                .body(result);
    }

    @PutMapping("/branches/update/{id}")
    public ResponseEntity<?> update(@RequestBody BranchDTO branchDTO, @PathVariable Long id) {
        if (branchDTO.getId() == null || !branchDTO.getId().equals(id)) {
            return ResponseEntity.badRequest().body("Invalid ID");
        }
        try {
            BranchDTO result = branchService.update(branchDTO);
            return ResponseEntity.ok().body(result);
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/branches/all-list/by-sort-order")
    public ResponseEntity<?> findAllByOrderBySortOrderDesc() {
        List<BranchDTO> branches = branchService.findAllByOrderBySortOrderDesc();
        return ResponseEntity.ok(branches);
    }

    @GetMapping("/branches/by/{id}")
    public ResponseEntity<?> byId(@PathVariable Long id) {
        Branch branch = branchService.findById(id);
        return ResponseEntity.ok(branch);
    }

    @GetMapping("/branches/all-by/{parentId}")
    public ResponseEntity<?> parentId(@PathVariable Long parentId) {
        List<BranchDTO> branch = branchService.findAllByParentId(parentId);
        return ResponseEntity.ok(branch);
    }

    @GetMapping("/branches/by-structure/{structureId}")
    public ResponseEntity<?> findByStructureId(@PathVariable Long structureId) {
        List<BranchDTO> branchDTOS = branchService.findBranchByStructureId(structureId);
        return ResponseEntity.ok(branchDTOS);
    }

    @GetMapping("/branches/by-structure/list/{structureId}")
    public ResponseEntity<?> findAllStructureAndBranchByStructureId(@PathVariable Long structureId) {
        StructureBranchList branchDTOS = branchService.findAllStructureAndBranchByStructureId(structureId);
        return ResponseEntity.ok(branchDTOS);
    }

    @DeleteMapping("/branches/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        branchService.delete(id);
        return ResponseEntity.ok("Deleted");
    }

    @GetMapping("/branches/by-region/{regionId}")
    public ResponseEntity<?> findByRegionId(@PathVariable Long regionId) {
        List<BranchDTO> branchDTOS = branchService.findByRegionId(regionId);
        return ResponseEntity.ok(branchDTOS);
    }

    @GetMapping("/branches/by-status/Active")
    public ResponseEntity<?> findByStatusActive() {
        List<BranchDTO> branchDTOS = branchService.findByStatusActive();
        return ResponseEntity.ok(branchDTOS);
    }

    @GetMapping("/branches/by-status/InActive")
    public ResponseEntity<?> findByStatusInActive() {
        List<BranchDTO> branchDTOS = branchService.findByStatusInActive();
        return ResponseEntity.ok(branchDTOS);
    }

    @GetMapping("/branches/by-status/active/count")
    public ResponseEntity<?> findByStatusInActiveCount() {
        Long branchDTOS = branchService.countBranchesByStatus();
        return ResponseEntity.ok(branchDTOS);
    }

}
