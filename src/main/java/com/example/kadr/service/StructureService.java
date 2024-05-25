/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:15:10
 */
package com.example.kadr.service;

import com.example.kadr.entity.Structure;
import com.example.kadr.entity.enumitation.hr.CommonStatus;
import com.example.kadr.service.dto.StructureBranchList;
import com.example.kadr.service.dto.StructureDTO;
import jakarta.transaction.Transactional;

import java.util.List;

public interface StructureService {
    String create(StructureDTO structureDTO);

//    String update(StructureDTO structureDTO);

    String updateNew(StructureDTO structureDTO);

    List<StructureDTO> all();

    List<StructureDTO> findAllByOrderBySortOrderDesc();

    List<StructureDTO> findAllByParentId(Long parentId);

    Structure findById(Long id);

    @Transactional
    void delete(Long id);

    List<StructureDTO> findAllByParentIsNull();

    List<StructureDTO> findAllHrStructureList(Long parentId);

    List<StructureDTO> findAllList();
}
