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
import jakarta.transaction.Transactional;
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
    public DistrictDTO create(DistrictDTO districtDTO) {
        District district = districtMapper.toEntity(districtDTO);
        district = districtRepository.save(district);
        return districtMapper.toDTO(district);
    }

    @Override
    public DistrictDTO update(DistrictDTO districtDTO) {
        District district = districtMapper.toEntity(districtDTO);
        district = districtRepository.save(district);
        return districtMapper.toDTO(district);
    }

    @Override
    public List<DistrictDTO> findAllByOrderBySortOrderDesc() {
        return districtMapper.toDTOS(districtRepository.findAllByOrderBySortOrderDesc());
    }

    @Override
    public District findById(Long id) {
        return districtRepository.findById(id).orElseGet(District::new);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        districtRepository.updateStatus(id, CommonStatus.DELETED);
    }

}
