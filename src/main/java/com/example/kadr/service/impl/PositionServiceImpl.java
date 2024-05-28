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
import jakarta.transaction.Transactional;
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
    @Transactional
    public void delete(Long id) {
        positionRepository.updateStatus(id, CommonStatus.DELETED);
    }

    public Position findById(Long id) {
        return positionRepository.findById(id).orElseGet(Position::new);
    }

    public List<PositionDTO> findActivePositionsOrderBySortOrder() {
        return positionMapper.toDTOS(positionRepository.findActivePositionsOrderBySortOrder());
    }

    public List<PositionDTO> findByPositionsOrderBySortOrder() {
        return positionMapper.toDTOS(positionRepository.findByPositionsOrderBySortOrder());
    }

    public List<PositionDTO> findByName(String name) {
        return positionMapper.toDTOS(positionRepository.findByName(name));
    }

    public List<PositionDTO> findPositionsByStatuses(List<CommonStatus> statuses) {
        return positionMapper.toDTOS(positionRepository.findPositionsByStatuses(statuses));
    }

    public List<PositionDTO> findPositionsByNameContaining(String name) {
        return positionMapper.toDTOS(positionRepository.findPositionsByNameContaining(name));
    }

    public List<PositionDTO> findPositionsBySortOrderBetween(Long start, Long end) {
        return positionMapper.toDTOS(positionRepository.findPositionsBySortOrderBetween(start, end));
    }

    public List<PositionDTO> findPositionsByNameAndStatus(String name, CommonStatus status) {
        return positionMapper.toDTOS(positionRepository.findPositionsByNameAndStatus(name, status));
    }

    public List<PositionDTO> findPositionsWithMaxSortOrders() {
        return positionMapper.toDTOS(positionRepository.findPositionsWithMaxSortOrders());
    }
}
