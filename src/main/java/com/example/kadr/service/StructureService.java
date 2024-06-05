/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:15:10
 */
package com.example.kadr.service;

import com.example.kadr.entity.Structure;
import com.example.kadr.service.dto.StructureDTO;
import jakarta.transaction.Transactional;

import java.util.List;

public interface StructureService {
    StructureDTO create(StructureDTO structureDTO);

//    String update(StructureDTO structureDTO);

    StructureDTO update(StructureDTO structureDTO);

    List<StructureDTO> all();

    List<StructureDTO> findAllByOrderBySortOrderDesc();

    List<StructureDTO> findAllByParentId(Long parentId);

    Structure findById(Long id);

    @Transactional
    void delete(Long id);

    List<StructureDTO> findAllByParentIsNull();

    List<StructureDTO> findAllHrStructureList(Long parentId);

    List<StructureDTO> findAllListActive();
}
