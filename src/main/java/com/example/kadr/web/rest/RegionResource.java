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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/region/")
public class RegionResource {
    private final RegionService regionService;

    public RegionResource(RegionService regionService) {
        this.regionService = regionService;
    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody RegionDTO regionDTO){
        String response = regionService.create(regionDTO);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody RegionDTO regionDTO){
        String response = regionService.update(regionDTO);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/all")
    public ResponseEntity<?> all(){
        List<RegionDTO> regions = regionService.all();
        return ResponseEntity.ok(regions);
    }
    @GetMapping("/by-id/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Region region = regionService.findById(id);
        return ResponseEntity.ok(region);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        regionService.delete(id);
        return ResponseEntity.ok("o'chirildi");
    }
}
