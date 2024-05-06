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
import com.example.kadr.service.mapper.PositionMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {
    private final PositionRepository positionRepository;
    private final PositionMapper positionMapper;

    public PositionServiceImpl(PositionRepository positionRepository, PositionMapper positionMapper) {
        this.positionRepository = positionRepository;
        this.positionMapper = positionMapper;
    }

    @Override
    public String create(PositionDTO positionDTO) {
        try {
            positionRepository.save(positionMapper.toEntity(positionDTO));
            return "Muvaffaqiyatli saqlandi";
        } catch (Exception e) {
            e.printStackTrace();
            return "Xatolik";
        }
    }

    @Override
    public String update(PositionDTO positionDTO) {
        try {
            positionRepository.save(positionMapper.toEntity(positionDTO));
            return "Muvaffaqiyatli uzgartirildi";
        } catch (Exception e) {
            e.printStackTrace();
            return "Xatolik";
        }
    }

    @Override
    public List<PositionDTO> all() {
        return positionMapper.toDTOS(positionRepository.findAll());
    }

    @Override
    public void delete(Long id) {
        positionRepository.updateStatus(id, CommonStatus.DELETED);
    }

    public Position findById(Long id) {
        return positionRepository.findById(id).orElseGet(Position::new);
    }


}
