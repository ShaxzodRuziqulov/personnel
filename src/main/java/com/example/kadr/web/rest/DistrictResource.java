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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/district/")
public class DistrictResource {
    private final DistrictService districtService;

    public DistrictResource(DistrictService districtService) {
        this.districtService = districtService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DistrictDTO districtDTO){
        String response = districtService.create(districtDTO);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody DistrictDTO districtDTO){
        String response = districtService.update(districtDTO);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/all")
    public ResponseEntity<?> all(){
        List<District> branches = districtService.all();
        return ResponseEntity.ok(branches);
    }
    @GetMapping("/by-id/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        District district = districtService.findById(id);
        return ResponseEntity.ok(district);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        districtService.delete(id);
        return ResponseEntity.ok("o'chirildi");
    }
}
