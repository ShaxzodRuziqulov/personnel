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
    public String create(RegionDTO regionDTO) {
        try {
            regionRepository.save(regionMapper.toEntity(regionDTO));
            return "Muvoffaqiyatli saqlandi";
        } catch (Exception e) {
            e.printStackTrace();
            return "Xatolik";
        }
    }

    @Override
    public String update(RegionDTO regionDTO) {
        try {
            regionRepository.save(regionMapper.toEntity(regionDTO));
            return "Muvaffaqiyatli uzgartirildi";
        } catch (Exception e) {
            e.printStackTrace();
            return "Xatolik";
        }
    }

    @Override
    public List<RegionDTO> all() {
        return regionMapper.toDTOS(regionRepository.findAll());
    }

    @Override
    public Region findById(Long id) {
        return regionRepository.findById(id).orElseGet(Region::new);
    }

    @Override
    public void delete(Long id) {
        regionRepository.updateStatus(id, CommonStatus.DELETED);
    }
}