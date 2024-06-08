/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:06.05.2024
 * TIME:21:06
 */
package com.example.kadr.service.mapper;

import com.example.kadr.entity.District;
import com.example.kadr.entity.enumitation.hr.CommonStatus;
import com.example.kadr.service.dto.DistrictDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DistrictMapper {
    public List<DistrictDTO> toDTOS(List<District> districts) {
        return districts.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public DistrictDTO toDTO(District district) {
        DistrictDTO districtDTO = new DistrictDTO();
        districtDTO.setId(district.getId());
        districtDTO.setName(district.getName());
        districtDTO.setStatus(String.valueOf(district.getStatus()));
        districtDTO.setSortOrder(district.getSortOrder());
        return districtDTO;
    }

    public District toEntity(DistrictDTO districtDTO) {
        District district = new District();
        district.setId(districtDTO.getId());
        district.setSortOrder(districtDTO.getSortOrder());
        district.setName(districtDTO.getName());
        district.setStatus(CommonStatus.valueOf(districtDTO.getStatus()));
        return district;
    }
}
