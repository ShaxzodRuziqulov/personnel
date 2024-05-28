/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:22:29
 */
package com.example.kadr.web.rest;

import com.example.kadr.entity.Position;
import com.example.kadr.entity.enumitation.hr.CommonStatus;
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
    @GetMapping("/by-status/active")
    public ResponseEntity<?> findActivePositionsOrderBySortOrder(){
        List<PositionDTO> activePosition = positionService.findActivePositionsOrderBySortOrder();
        return ResponseEntity.ok(activePosition);
    }
    @GetMapping("/by-sortOrder")
    public ResponseEntity<?> findByPositionsOrderBySortOrder(){
        List<PositionDTO> findSortOrder= positionService.findByPositionsOrderBySortOrder();
        return ResponseEntity.ok(findSortOrder);
    }
    @GetMapping("/by-name/{name}")
    public ResponseEntity<?> findByName (@PathVariable String name){
        List<PositionDTO> findByName = positionService.findByName(name);
        return ResponseEntity.ok(findByName);
    }
    @GetMapping("/by-statuses/{statuses}")
    public ResponseEntity<?> findPositionsByStatuses(@PathVariable List<CommonStatus> statuses){
        List<PositionDTO> findPositionsByStatuses= positionService.findPositionsByStatuses(statuses);
        return ResponseEntity.ok(findPositionsByStatuses);
    }
    @GetMapping("/by-positionName/{name}")
    public ResponseEntity<?> findPositionsByNameContaining(@PathVariable String name){
        List<PositionDTO> findPositionsByNameContaining = positionService.findPositionsByNameContaining(name);
        return ResponseEntity.ok(findPositionsByNameContaining);
    }
    @GetMapping("/by-positionBetween/{start}/{end}")
    public ResponseEntity<?> findPositionsBySortOrderBetween(@PathVariable Long start,@PathVariable Long end){
        List<PositionDTO> findPositionsBySortOrderBetween = positionService.findPositionsBySortOrderBetween(start,end);
        return ResponseEntity.ok(findPositionsBySortOrderBetween);
    }
    @GetMapping("/byNameAndStatus/{name}/{status}")
    public ResponseEntity<?> findPositionsByNameAndStatus(@PathVariable String name,@PathVariable CommonStatus status){
        List<PositionDTO> findPositionsByNameAndStatus =positionService.findPositionsByNameAndStatus(name,status);
        return ResponseEntity.ok(findPositionsByNameAndStatus);
    }
    @GetMapping("/maxSortOrders")
    public ResponseEntity<?> findPositionsWithMaxSortOrders(){
        List<PositionDTO> findPositionsWithMaxSortOrders = positionService.findPositionsWithMaxSortOrders();
        return ResponseEntity.ok(findPositionsWithMaxSortOrders);
    }


}
