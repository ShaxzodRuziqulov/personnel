/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:24.04.2024
 * TIME:22:58
 */
package com.example.kadr.service;

import com.example.kadr.entity.District;
import com.example.kadr.service.dto.DistrictDTO;
import jakarta.transaction.Transactional;

import java.util.List;

public interface DistrictService {
    String create(DistrictDTO districtDTO);
    String update(DistrictDTO districtDTO);
    List<DistrictDTO> all();
    District findById(Long id);
    @Transactional
    void delete(Long id);
}
