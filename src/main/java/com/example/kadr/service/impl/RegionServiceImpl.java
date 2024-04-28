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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegionServiceImpl implements RegionService {
    private final RegionRepository regionRepository;

    public RegionServiceImpl(RegionRepository repository) {
        this.regionRepository = repository;
    }

    @Override
    public String create(RegionDTO regionDTO) {
        try {
            regionRepository.save(toEntity(regionDTO));
            return "Muvoffaqiyatli saqlandi";
        } catch (Exception e) {
            e.printStackTrace();
            return "Xatolik";
        }
    }

    @Override
    public String update(RegionDTO regionDTO) {
        try {
            regionRepository.save(toEntity(regionDTO));
            return "Muvaffaqiyatli uzgartirildi";
        } catch (Exception e) {
            e.printStackTrace();
            return "Xatolik";
        }
    }

    @Override
    public List<RegionDTO> all() {
        return toDTOS(regionRepository.findAll());
    }

    @Override
    public Region findById(Long id) {
        return regionRepository.findById(id).orElseGet(Region::new);
    }

    @Override
    public void delete(Long id) {
        regionRepository.updateStatus(id, CommonStatus.DELETED);
    }

    public Region toEntity(RegionDTO regionDTO) {
        Region region = new Region();
        region.setId(regionDTO.getId());
        region.setName(regionDTO.getName());
        region.setStatus(CommonStatus.valueOf(regionDTO.getStatus()));
        return region;
    }

    public List<RegionDTO> toDTOS(List<Region> regions) {
        List<RegionDTO> regionDTOList = new ArrayList<>();
        for (Region region :
                regions) {
            RegionDTO regionDTO = new RegionDTO();
            regionDTO.setId(region.getId());
            regionDTO.setName(region.getName());
            regionDTO.setStatus(String.valueOf(region.getStatus()));
            regionDTOList.add(regionDTO);
        }
        return regionDTOList;
    }
}
