/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:15:10
 */
package com.example.kadr.service;

import com.example.kadr.entity.Structure;
import com.example.kadr.service.dto.StructureDTO;

import java.util.List;

public interface StructureService {
    String create(StructureDTO structureDTO);

    String update(StructureDTO structureDTO);

    String updateNew(StructureDTO structureDTO);

    List<StructureDTO> all();
    List<StructureDTO> findAllByOrderBySortOrderAsc();

    List<StructureDTO> findAllByParentId(Long parentId);
    Structure findById (Long id);
    void delete(Long id);
}
