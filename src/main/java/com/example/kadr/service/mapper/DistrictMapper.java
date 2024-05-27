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
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DistrictMapper {
    public List<DistrictDTO> toDTOS(List<District> districts) {
        List<DistrictDTO> districtList = new ArrayList<>();
        for (District district : districts) {
            DistrictDTO districtDTO = new DistrictDTO();
            districtDTO.setId(district.getId());
            districtDTO.setSortOrder(district.getSortOrder());
            districtDTO.setName(district.getName());
            districtDTO.setStatus(String.valueOf(district.getStatus()));
            districtList.add(districtDTO);
        }
        return districtList;
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
