/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:26.04.2024
 * TIME:11:50
 */
package com.example.kadr.web.rest;

import com.example.kadr.entity.Region;
import com.example.kadr.service.RegionService;
import com.example.kadr.service.dto.RegionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RegionResource {
    private final RegionService regionService;

    public RegionResource(RegionService regionService) {
        this.regionService = regionService;
    }

    @PostMapping("/regions/create")
    public ResponseEntity<?> create(@RequestBody RegionDTO regionDTO) throws URISyntaxException {
        RegionDTO result = regionService.create(regionDTO);
        return ResponseEntity
                .created(new URI("/api/regions/create"))
                .body(result);
    }

    @PutMapping("/regions/update/{id}")
    public ResponseEntity<?> update(@RequestBody RegionDTO regionDTO, @PathVariable Long id) {
        if (regionDTO == null) {
            return ResponseEntity.badRequest().body("RegionDTO null");
        }
        if (regionDTO.getId() == null) {
            return ResponseEntity.badRequest().body("RegionDTO ID null");
        }
        if (!regionDTO.getId().equals(id)) {
            return ResponseEntity.badRequest().body("Invalid ID: RegionDTO ID URL IDga mos kelmaydi");
        }
        try {
            RegionDTO result = regionService.update(regionDTO);
            return ResponseEntity.ok().body(result);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/regions/all-list/by-sort-order")
    public ResponseEntity<?> findAllByOrderBySortOrderDesc() {
        List<RegionDTO> regions = regionService.findAllByOrderBySortOrderDesc();
        return ResponseEntity.ok(regions);
    }

    @GetMapping("/regions/by/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Region region = regionService.findById(id);
        return ResponseEntity
                .ok(region);
    }

    @DeleteMapping("/regions/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        regionService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
