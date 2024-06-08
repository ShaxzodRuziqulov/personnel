/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:24.04.2024
 * TIME:22:49
 */
package com.example.kadr.service.impl;

import com.example.kadr.entity.Region;
import com.example.kadr.entity.enumitation.hr.CommonStatus;
import com.example.kadr.repository.RegionRepository;
import com.example.kadr.service.RegionService;
import com.example.kadr.service.dto.RegionDTO;
import com.example.kadr.service.mapper.RegionMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServiceImpl implements RegionService {
    private final RegionRepository regionRepository;
    private final RegionMapper regionMapper;

    public RegionServiceImpl(RegionRepository repository, RegionMapper regionMapper) {
        this.regionRepository = repository;
        this.regionMapper = regionMapper;
    }

    @Override
    public RegionDTO create(RegionDTO regionDTO) {
        Region region = regionMapper.toEntity(regionDTO);
        region = regionRepository.save(region);
        return regionMapper.toDTO(region);
    }

    @Override
    public RegionDTO update(RegionDTO regionDTO) {
        Region region = regionMapper.toEntity(regionDTO);
        region = regionRepository.save(region);
        return regionMapper.toDTO(region);
    }

    @Override
    public List<RegionDTO> findAllByOrderBySortOrderDesc() {
        return regionMapper.toDTOS(regionRepository.findAllByOrderBySortOrderDesc());
    }

    @Override
    public Region findById(Long id) {
        return regionRepository.findById(id).orElseGet(Region::new);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        regionRepository.updateStatus(id, CommonStatus.DELETED);
    }
}