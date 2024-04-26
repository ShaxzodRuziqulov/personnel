/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:22:29
 */
package com.example.kadr.web.rest;

import com.example.kadr.entity.Position;
import com.example.kadr.entity.request.ReqPosition;
import com.example.kadr.service.PositionService;
import com.example.kadr.service.dto.PositionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/position")
public class PositionResource {
    private final PositionService positionService;

    public PositionResource(PositionService positionService) {
        this.positionService = positionService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody PositionDTO positionDTO) {
        String response = positionService.create(positionDTO);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody PositionDTO positionDTO) {
        String response = positionService.update(positionDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<?> all() {
        List<PositionDTO> positionDTOS = positionService.all();
        return ResponseEntity.ok(positionDTOS);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        positionService.delete(id);
        return ResponseEntity.ok("o'chirildi");
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Position positionDTOS = positionService.findById(id);
        return ResponseEntity.ok(positionDTOS);
    }
}
