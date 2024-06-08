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
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PositionResource {
    private final PositionService positionService;

    public PositionResource(PositionService positionService) {
        this.positionService = positionService;
    }

    @PostMapping("/positions/created")
    public ResponseEntity<?> create(@RequestBody PositionDTO positionDTO) throws URISyntaxException {
        PositionDTO result = positionService.create(positionDTO);
        return ResponseEntity.created(new URI("/api/positions/created")).body(result);
    }

    @PutMapping("/positions/update")
    public ResponseEntity<?> update(@RequestBody PositionDTO positionDTO) {
        try {
            PositionDTO result = positionService.update(positionDTO);
            return ResponseEntity.ok(result);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/positions/all")
    public ResponseEntity<?> all() {
        List<PositionDTO> positionDTOS = positionService.all();
        return ResponseEntity.ok(positionDTOS);
    }

    @DeleteMapping("/positions/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        positionService.delete(id);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/positions/by/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Position positionDTOS = positionService.findById(id);
        return ResponseEntity.ok(positionDTOS);
    }

    @GetMapping("/positions/by-status/active")
    public ResponseEntity<?> findActivePositionsOrderBySortOrder() {
        List<PositionDTO> activePosition = positionService.findActivePositionsOrderBySortOrder();
        return ResponseEntity.ok(activePosition);
    }

    @GetMapping("/positions/by-sortOrder")
    public ResponseEntity<?> findByPositionsOrderBySortOrder() {
        List<PositionDTO> findSortOrder = positionService.findByPositionsOrderBySortOrder();
        return ResponseEntity.ok(findSortOrder);
    }

    @GetMapping("/positions/by/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name) {
        List<PositionDTO> findByName = positionService.findByName(name);
        return ResponseEntity.ok(findByName);
    }

    @GetMapping("/positions/by-statuses/{statuses}")
    public ResponseEntity<?> findPositionsByStatuses(@PathVariable List<CommonStatus> statuses) {
        List<PositionDTO> findPositionsByStatuses = positionService.findPositionsByStatuses(statuses);
        return ResponseEntity.ok(findPositionsByStatuses);
    }

    @GetMapping("/positions/by-positionName/{name}")
    public ResponseEntity<?> findPositionsByNameContaining(@PathVariable String name) {
        List<PositionDTO> findPositionsByNameContaining = positionService.findPositionsByNameContaining(name);
        return ResponseEntity.ok(findPositionsByNameContaining);
    }

    @GetMapping("/positions/by-positionBetween/{start}/{end}")
    public ResponseEntity<?> findPositionsBySortOrderBetween(@PathVariable Long start, @PathVariable Long end) {
        List<PositionDTO> findPositionsBySortOrderBetween = positionService.findPositionsBySortOrderBetween(start, end);
        return ResponseEntity.ok(findPositionsBySortOrderBetween);
    }

    @GetMapping("/positions/byNameAndStatus/{name}/{status}")
    public ResponseEntity<?> findPositionsByNameAndStatus(@PathVariable String name, @PathVariable CommonStatus status) {
        List<PositionDTO> findPositionsByNameAndStatus = positionService.findPositionsByNameAndStatus(name, status);
        return ResponseEntity.ok(findPositionsByNameAndStatus);
    }

    @GetMapping("/positions/maxSortOrders")
    public ResponseEntity<?> findPositionsWithMaxSortOrders() {
        List<PositionDTO> findPositionsWithMaxSortOrders = positionService.findPositionsWithMaxSortOrders();
        return ResponseEntity.ok(findPositionsWithMaxSortOrders);
    }


}
