/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:06.05.2024
 * TIME:21:00
 */
package com.example.kadr.service.mapper;

import com.example.kadr.entity.Position;
import com.example.kadr.entity.enumitation.hr.CommonStatus;
import com.example.kadr.service.dto.PositionDTO;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PositionMapper {
    public Position toEntity(PositionDTO positionDTO) {
        Position position = new Position();
        position.setId(positionDTO.getId());
        position.setSortOrder(positionDTO.getSortOrder());
        position.setName(positionDTO.getName());
        position.setStatus(CommonStatus.valueOf(positionDTO.getStatus()));
        return position;
    }

    public List<PositionDTO> toDTOS(List<Position> positions) {
        return positions.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public PositionDTO toDTO(Position position) {
        PositionDTO positionDTO = new PositionDTO();
        positionDTO.setId(position.getId());
        positionDTO.setName(position.getName());
        positionDTO.setStatus(String.valueOf(position.getStatus()));
        positionDTO.setSortOrder(position.getSortOrder());
        return positionDTO;
    }
}
