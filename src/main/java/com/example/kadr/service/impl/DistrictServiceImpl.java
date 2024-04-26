/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:24.04.2024
 * TIME:23:00
 */
package com.example.kadr.service.impl;

import com.example.kadr.entity.District;
import com.example.kadr.repository.DistrictRepository;
import com.example.kadr.repository.RegionRepository;
import com.example.kadr.service.DistrictService;
import com.example.kadr.service.dto.DistrictDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DistrictServiceImpl implements DistrictService {
    private final DistrictRepository districtRepository;
    private final RegionRepository regionRepository;

    public DistrictServiceImpl(DistrictRepository districtRepository, RegionRepository regionRepository) {
        this.districtRepository = districtRepository;
        this.regionRepository = regionRepository;
    }

    @Override
    public String create(DistrictDTO districtDTO) {
        try {
            District district = new District();
            district.setName(districtDTO.getName());
            districtRepository.save(district);
            return "Muvaffaqiyatli saqlandi";
        } catch (Exception e) {
            e.printStackTrace();
            return "Xatolik";
        }
    }

    @Override
    public String update(DistrictDTO districtDTO) {
        try {
            if (districtDTO.getId() != null) {
                if (districtRepository.findById(districtDTO.getId()).isPresent()) {
                    District district = districtRepository.findById(districtDTO.getId()).get();
                    district.setName(districtDTO.getName());
                    districtRepository.save(district);
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
    public List<District> all() {
        return districtRepository.findAll();
    }

    @Override
    public District findById(Long id) {
        return districtRepository.findById(id).orElseGet(District::new);
    }

    @Override
    public void delete(Long id) {
        District district = districtRepository.getReferenceById(id);
        districtRepository.delete(district);
    }

}
