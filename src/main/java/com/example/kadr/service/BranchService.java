/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:15:40
 */
package com.example.kadr.service;

import com.example.kadr.entity.Branch;
import com.example.kadr.service.dto.BranchDTO;
import com.example.kadr.service.dto.StructureBranchList;

import java.util.List;

public interface BranchService {
    String create(BranchDTO branchDTO);

    String update(BranchDTO branchDTO);

    List<BranchDTO> all();

    void delete(Long id);

    Branch findById(Long id);

    List<BranchDTO> findAllByParentId(Long parentId);

    List<BranchDTO> findBranchByStructureId(Long structureId);

    StructureBranchList findAllStructureAndBranchByStructureId(Long structureId);

    List<BranchDTO> findByRegionId(Long id);

    List<BranchDTO> findByStatusActive();

    List<BranchDTO> findByStatusInActive();

    Long countBranchesByStatus();
}
