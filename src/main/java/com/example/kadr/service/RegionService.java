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
    String create(RegionDTO regionDTO);
    String update(RegionDTO regionDTO);
    List<RegionDTO> all();
    Region findById(Long id);
    @Transactional
    void delete(Long id);
}
