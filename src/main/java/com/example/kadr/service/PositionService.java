/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:19:16
 */
package com.example.kadr.service;

import com.example.kadr.entity.Position;
import com.example.kadr.service.dto.PositionDTO;

import java.util.List;

public interface PositionService {
    String create(PositionDTO positionDTO);

    String update(PositionDTO positionDTO);

    List<PositionDTO> all();
    Position findById(Long id);

    void delete(Long id);
}
