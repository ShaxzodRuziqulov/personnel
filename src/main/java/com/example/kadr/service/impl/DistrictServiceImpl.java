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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {
    private final DistrictRepository districtRepository;

    public DistrictServiceImpl(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }


    @Override
    public String create(DistrictDTO districtDTO) {
        try {
            districtRepository.save(toEntity(districtDTO));
            return "Muvaffaqiyatli saqlandi";
        } catch (Exception e) {
            e.printStackTrace();
            return "Xatolik";
        }
    }

    @Override
    public String update(DistrictDTO districtDTO) {
        try {
            districtRepository.save(toEntity(districtDTO));
            return "Muvaffaqiyatli uzgartirildi";
        } catch (Exception e) {
            e.printStackTrace();
            return "Xatolik";
        }
    }

    @Override
    public List<DistrictDTO> all() {
        return toDTOS(districtRepository.findAll());
    }

    @Override
    public District findById(Long id) {
        return districtRepository.findById(id).orElseGet(District::new);
    }

    @Override
    public void delete(Long id) {
        districtRepository.updateStatus(id, CommonStatus.DELETED);
    }

    public List<DistrictDTO> toDTOS(List<District> districts) {
        List<DistrictDTO> districtList = new ArrayList<>();
        for (District district : districts) {
            DistrictDTO districtDTO = new DistrictDTO();
            districtDTO.setId(district.getId());
            districtDTO.setName(district.getName());
            districtDTO.setStatus(String.valueOf(district.getStatus()));
            districtList.add(districtDTO);
        }
        return districtList;
    }

    public District toEntity(DistrictDTO districtDTO) {
        District district = new District();
        district.setId(districtDTO.getId());
        district.setName(districtDTO.getName());
        district.setStatus(CommonStatus.valueOf(districtDTO.getStatus()));
        return district;
    }

}
