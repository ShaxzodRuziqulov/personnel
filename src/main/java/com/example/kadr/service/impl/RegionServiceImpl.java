/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:24.04.2024
 * TIME:22:49
 */
package com.example.kadr.service.impl;

import com.example.kadr.entity.Region;
import com.example.kadr.repository.RegionRepository;
import com.example.kadr.service.RegionService;
import com.example.kadr.service.dto.RegionDTO;
import org.springframework.stereotype.Service;

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
            Region region = new Region();
            region.setName(regionDTO.getName());
            regionRepository.save(region);
            return "Muvoffaqiyatli saqlandi";
        } catch (Exception e) {
            e.printStackTrace();
            return "Xatolik";
        }
    }

    @Override
    public String update(RegionDTO regionDTO) {
        try {
            if (regionDTO.getId() != null) {
                if (regionRepository.findById(regionDTO.getId()).isPresent()) {
                    Region region = regionRepository.findById(regionDTO.getId()).get();
                    region.setName(regionDTO.getName());
                    regionRepository.save(region);
                    return "Muvaffaqiyatli uzgartirildi";
                } else {
                    return "Id topilmadi";
                }
            } else {
                return "Xatolik: id null ga teng";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Xatolik";
        }
    }

    @Override
    public List<Region> all() {
        return regionRepository.findAll();
    }

    @Override
    public Region findById(Long id) {
        return regionRepository.findById(id).orElseGet(Region::new);
    }

    @Override
    public void delete(Long id) {
        Region region = regionRepository.getReferenceById(id);
        regionRepository.delete(region);
    }
}
