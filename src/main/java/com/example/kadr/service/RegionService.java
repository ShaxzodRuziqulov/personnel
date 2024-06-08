/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:24.04.2024
 * TIME:22:47
 */
package com.example.kadr.service;

import com.example.kadr.entity.Region;
import com.example.kadr.service.dto.RegionDTO;
import jakarta.transaction.Transactional;

import java.util.List;

public interface RegionService {
    RegionDTO create(RegionDTO regionDTO);

    RegionDTO update(RegionDTO regionDTO);

    List<RegionDTO> findAllByOrderBySortOrderDesc();

    Region findById(Long id);

    void delete(Long id);
}
