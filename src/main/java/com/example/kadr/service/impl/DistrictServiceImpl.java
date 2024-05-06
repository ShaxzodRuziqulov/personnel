/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:24.04.2024
 * TIME:23:00
 */
package com.example.kadr.service.impl;

import com.example.kadr.entity.District;
import com.example.kadr.entity.enumitation.hr.CommonStatus;
import com.example.kadr.repository.DistrictRepository;
import com.example.kadr.service.DistrictService;
import com.example.kadr.service.dto.DistrictDTO;
import com.example.kadr.service.mapper.DistrictMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {
    private final DistrictRepository districtRepository;
    private final DistrictMapper districtMapper;

    public DistrictServiceImpl(DistrictRepository districtRepository, DistrictMapper districtMapper) {
        this.districtRepository = districtRepository;
        this.districtMapper = districtMapper;
    }

    @Override
    public String create(DistrictDTO districtDTO) {
        try {
            districtRepository.save(districtMapper.toEntity(districtDTO));
            return "Muvaffaqiyatli saqlandi";
        } catch (Exception e) {
            e.printStackTrace();
            return "Xatolik";
        }
    }

    @Override
    public String update(DistrictDTO districtDTO) {
        try {
            districtRepository.save(districtMapper.toEntity(districtDTO));
            return "Muvaffaqiyatli uzgartirildi";
        } catch (Exception e) {
            e.printStackTrace();
            return "Xatolik";
        }
    }

    @Override
    public List<DistrictDTO> all() {
        return districtMapper.toDTOS(districtRepository.findAll());
    }

    @Override
    public District findById(Long id) {
        return districtRepository.findById(id).orElseGet(District::new);
    }

    @Override
    public void delete(Long id) {
        districtRepository.updateStatus(id, CommonStatus.DELETED);
    }

}
