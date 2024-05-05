/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:15:40
 */
package com.example.kadr.service;

import com.example.kadr.entity.Branch;
import com.example.kadr.service.dto.BranchDTO;

import java.util.List;
import java.util.Optional;

public interface BranchService {
    String create(BranchDTO branchDTO);

    String update(BranchDTO branchDTO);

    List<BranchDTO> all();

    void delete(Long id);

    Branch findById(Long id);

    List<Branch> findAllByParentId(Long parentId);

    List<Branch> findByStructureId(Long structureId);

}
