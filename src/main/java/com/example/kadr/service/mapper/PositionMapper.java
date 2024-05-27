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
        List<PositionDTO> positionDTOS = new ArrayList<>();
        for (Position position : positions) {
            PositionDTO positionDTO = new PositionDTO();
            positionDTO.setId(position.getId());
            positionDTO.setSortOrder(position.getSortOrder());
            positionDTO.setName(position.getName());
            positionDTO.setStatus(String.valueOf(position.getStatus()));
            positionDTOS.add(positionDTO);
        }
        return positionDTOS;
    }
}
