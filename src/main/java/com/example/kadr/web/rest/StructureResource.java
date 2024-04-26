/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:21:58
 */
package com.example.kadr.web.rest;

import com.example.kadr.entity.Structure;
import com.example.kadr.service.StructureService;
import com.example.kadr.service.dto.StructureDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/structure")
public class StructureResource {
    private final StructureService structureService;

    public StructureResource(StructureService structureService) {
        this.structureService = structureService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody StructureDTO structureDTO) {
        String response = structureService.create(structureDTO);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody StructureDTO structureDTO) {
        String response = structureService.update(structureDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<?> all() {
        List<StructureDTO> structureDTOS = structureService.all();
        return ResponseEntity.ok(structureDTOS);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        structureService.delete(id);
        return ResponseEntity.ok("o'chirildi");
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Structure structure = structureService.findById(id);
        return ResponseEntity.ok(structure);
    }

    @GetMapping("/all-by/{parentId}")
    public ResponseEntity<?> all(@PathVariable Long parentId) {
        List<StructureDTO> structure = structureService.findAllByParentId(parentId);
        return ResponseEntity.ok(structure);
    }

}
