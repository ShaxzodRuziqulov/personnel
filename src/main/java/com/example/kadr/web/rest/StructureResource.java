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
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StructureResource {
    private final StructureService structureService;

    public StructureResource(StructureService structureService) {
        this.structureService = structureService;
    }

    @PostMapping("/structures/create")
    public ResponseEntity<?> create(@RequestBody StructureDTO structureDTO) throws URISyntaxException {

        StructureDTO result = structureService.create(structureDTO);
        return ResponseEntity
                .created(new URI("/api/structures/update/" + result.getId()))
                .body(result);
    }

//    @PutMapping("/update")
//    public ResponseEntity<?> update(@RequestBody StructureDTO structureDTO) {
//        String response = structureService.update(structureDTO);
//        return ResponseEntity.ok(response);
//    }

    @PutMapping("structures/update/{id}")
    public ResponseEntity<?> update(@RequestBody StructureDTO structureDTO,@PathVariable Long id){
        if (structureDTO.getId() == null || !structureDTO.getId().equals(id)) {
            return ResponseEntity.badRequest().body("Invalid ID");
        }
        try {
            StructureDTO result = structureService.update(structureDTO);
            return ResponseEntity
                    .ok()
                    .body(result);
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("structures/all-list/by-sort-order")
    public ResponseEntity<?> findAllByOrderBySortOrderDesc() {
        List<StructureDTO> structureDTOS = structureService.findAllByOrderBySortOrderDesc();
        return ResponseEntity
                .ok(structureDTOS);
    }

    @DeleteMapping("structures/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        structureService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping("structures/by/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Structure structure = structureService.findById(id);
        return ResponseEntity
                .ok(structure);
    }

    @GetMapping("structures/all-by/{parentId}")
    public ResponseEntity<?> all(@PathVariable Long parentId) {
        List<StructureDTO> structure = structureService.findAllByParentId(parentId);
        return ResponseEntity
                .ok(structure);
    }

    @GetMapping("structures/parents-null")
    public ResponseEntity<?> findAllByParentIsNull() {
        List<StructureDTO> parentIsNull = structureService.findAllByParentIsNull();
        return ResponseEntity
                .ok(parentIsNull);
    }

    @GetMapping("structures/list/{parentId}")
    public ResponseEntity<?> findAllHrStructureList(@PathVariable Long parentId) {
        List<StructureDTO> structureList = structureService.findAllHrStructureList(parentId);
        return ResponseEntity
                .ok(structureList);
    }

    @GetMapping("structures/all-list")
    public ResponseEntity<?> findAllListActive() {
        List<StructureDTO> findAllList = structureService.findAllListActive();
        return ResponseEntity
                .ok(findAllList);
    }

}
