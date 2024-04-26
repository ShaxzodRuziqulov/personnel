/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:19:23
 */
package com.example.kadr.service.impl;

import com.example.kadr.repository.PositionRepository;
import com.example.kadr.entity.Position;
import com.example.kadr.entity.request.ReqPosition;
import com.example.kadr.service.PositionService;
import com.example.kadr.service.dto.PositionDTO;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PositionServiceImpl implements PositionService {
    private final PositionRepository positionRepository;

    public PositionServiceImpl(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Override
    public String create(PositionDTO positionDTO) {
        try {
            Position position = new Position();
            position.setName(positionDTO.getName());
            return "Muvaffaqiyatli saqlandi";
        } catch (Exception e) {
            e.printStackTrace();
            return "Xatolik";
        }
    }

    @Override
    public String update(PositionDTO positionDTO) {
        try {
            if (positionDTO.getId() != null) {
                if (positionRepository.findById(positionDTO.getId()).isPresent()) {
                    Position position = positionRepository.findById(positionDTO.getId()).get();
                    position.setName(positionDTO.getName());
                    return "Muvaffaqiyatli uzgartirildi";
                } else {
                    return "Id topilmadi";
                }
            }else {
                return "Xatolik: id null ga teng";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Xatolik";
        }
    }

    @Override
    public List<PositionDTO> all() {
        return toDTOS(positionRepository.findAll());
    }

    @Override
    public void delete(Long id) {
        Position position = positionRepository.getReferenceById(id);
        positionRepository.delete(position);
    }

    public List<PositionDTO> toDTOS(List<Position> positions) {
        return positions.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private PositionDTO convertToDTO(Position position) {
        PositionDTO positionDTO = new PositionDTO();
        positionDTO.setId(position.getId());
        positionDTO.setName(position.getName());
        return positionDTO;
    }

    public Position findById(Long id) {
        return positionRepository.findById(id).orElseGet(Position::new);
    }
}
