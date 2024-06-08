/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:06.05.2024
 * TIME:21:08
 */
package com.example.kadr.service.mapper;

import com.example.kadr.entity.Region;
import com.example.kadr.entity.enumitation.hr.CommonStatus;
import com.example.kadr.service.dto.RegionDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RegionMapper {
    public Region toEntity(RegionDTO regionDTO) {
        Region region = new Region();
        region.setId(regionDTO.getId());
        region.setSortOrder(regionDTO.getSortOrder());
        region.setName(regionDTO.getName());
        region.setStatus(CommonStatus.valueOf(regionDTO.getStatus()));
        return region;
    }

    public List<RegionDTO> toDTOS(List<Region> regions) {
        return regions.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public RegionDTO toDTO(Region region) {
        RegionDTO regionDTO = new RegionDTO();
        regionDTO.setId(region.getId());
        regionDTO.setName(region.getName());
        regionDTO.setStatus(String.valueOf(region.getStatus()));
        regionDTO.setSortOrder(region.getSortOrder());
        return regionDTO;
    }
}
