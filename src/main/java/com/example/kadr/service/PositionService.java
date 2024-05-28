/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:19:16
 */
package com.example.kadr.service;

import com.example.kadr.entity.Position;
import com.example.kadr.entity.enumitation.hr.CommonStatus;
import com.example.kadr.service.dto.PositionDTO;
import jakarta.transaction.Transactional;

import java.util.List;

public interface PositionService {
    String create(PositionDTO positionDTO);

    String update(PositionDTO positionDTO);

    List<PositionDTO> all();

    Position findById(Long id);

    @Transactional
    void delete(Long id);

    List<PositionDTO> findActivePositionsOrderBySortOrder();

     List<PositionDTO> findByPositionsOrderBySortOrder();

     List<PositionDTO> findByName(String name);

     List<PositionDTO> findPositionsByStatuses(List<CommonStatus> statuses);

     List<PositionDTO> findPositionsByNameContaining(String name);

     List<PositionDTO> findPositionsBySortOrderBetween(Long start, Long end);

     List<PositionDTO> findPositionsByNameAndStatus(String name, CommonStatus status);

     List<PositionDTO> findPositionsWithMaxSortOrders();
}
