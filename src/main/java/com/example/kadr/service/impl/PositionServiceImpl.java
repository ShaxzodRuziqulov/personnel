/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:19:23
 */
package com.example.kadr.service.impl;

import com.example.kadr.entity.enumitation.hr.CommonStatus;
import com.example.kadr.repository.PositionRepository;
import com.example.kadr.entity.Position;
import com.example.kadr.service.PositionService;
import com.example.kadr.service.dto.PositionDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {
    private final PositionRepository positionRepository;

    public PositionServiceImpl(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Override
    public String create(PositionDTO positionDTO) {
        try {
            positionRepository.save(toEntity(positionDTO));
            return "Muvaffaqiyatli saqlandi";
        } catch (Exception e) {
            e.printStackTrace();
            return "Xatolik";
        }
    }

    @Override
    public String update(PositionDTO positionDTO) {
        try {
            positionRepository.save(toEntity(positionDTO));
            return "Muvaffaqiyatli uzgartirildi";
        } catch (Exception e) {
            e.printStackTrace();
            return "Xatolik";
        }
    }

    @Override
    public List<PositionDTO> all() {
        return toDTOS(positionRepository.findAll());
    }

    public List<PositionDTO> toDTOS(List<Position> positions) {
        List<PositionDTO> positionDTOS = new ArrayList<>();
        for (Position position : positions) {
            PositionDTO positionDTO = new PositionDTO();
            positionDTO.setId(position.getId());
            positionDTO.setName(position.getName());
            positionDTO.setStatus(String.valueOf(position.getStatus()));
            positionDTOS.add(positionDTO);
        }
        return positionDTOS;
    }

    @Override
    public void delete(Long id) {
        positionRepository.updateStatus(id, CommonStatus.DELETED);
    }


    public Position findById(Long id) {
        return positionRepository.findById(id).orElseGet(Position::new);
    }

    public Position toEntity(PositionDTO positionDTO) {
        Position position = new Position();
        position.setId(positionDTO.getId());
        position.setName(positionDTO.getName());
        position.setStatus(CommonStatus.valueOf(positionDTO.getStatus()));
        return position;
    }
}
