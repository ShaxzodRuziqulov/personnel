/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:26.04.2024
 * TIME:11:52
 */
package com.example.kadr.web.rest;

import com.example.kadr.entity.District;
import com.example.kadr.service.DistrictService;
import com.example.kadr.service.dto.DistrictDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DistrictResource {
    private final DistrictService districtService;

    public DistrictResource(DistrictService districtService) {
        this.districtService = districtService;
    }

    @PostMapping("/districts/create")
    public ResponseEntity<?> create(@RequestBody DistrictDTO districtDTO) throws URISyntaxException {
        DistrictDTO result = districtService.create(districtDTO);
        return ResponseEntity.created(new URI("api/districts/create")).body(result);
    }

    @PutMapping("/districts/update/{id}")
    public ResponseEntity<?> update(@RequestBody DistrictDTO districtDTO, @PathVariable Long id) {
        if (districtDTO.getId() == null || !districtDTO.getId().equals(id)) {
            return ResponseEntity.ok("Invalid ID");
        }
        try {
            DistrictDTO result = districtService.update(districtDTO);
            return ResponseEntity.ok(result);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/districts/all-list/by-sort-order")
    public ResponseEntity<?> findAllByOrderBySortOrderDesc() {
        List<DistrictDTO> branches = districtService.findAllByOrderBySortOrderDesc();
        return ResponseEntity.ok(branches);
    }

    @GetMapping("/districts/by/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        District district = districtService.findById(id);
        return ResponseEntity.ok(district);
    }

    @DeleteMapping("/districts/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        districtService.delete(id);
        return ResponseEntity.notFound().build();
    }
}
