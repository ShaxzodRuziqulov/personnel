/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:15:41
 */
package com.example.kadr.service.impl;

import com.example.kadr.entity.enumitation.hr.CommonStatus;
import com.example.kadr.repository.BranchRepository;
import com.example.kadr.entity.Branch;
import com.example.kadr.service.BranchService;
import com.example.kadr.service.StructureService;
import com.example.kadr.service.dto.BranchDTO;
import com.example.kadr.service.dto.StructureBranchList;
import com.example.kadr.service.mapper.BranchMapper;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchServiceImpl implements BranchService {
    private final Logger log = LoggerFactory.getLogger(BranchService.class);
    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper;
    private final StructureService structureService;

    public BranchServiceImpl(
            BranchRepository branchRepository,
            BranchMapper branchMapper,
            StructureService structureService
    ) {
        this.branchRepository = branchRepository;
        this.branchMapper = branchMapper;
        this.structureService = structureService;
    }


    @Override
    public BranchDTO create(BranchDTO branchDTO) {
        if (branchDTO.getSortOrder() == null) {
            if (branchDTO.getParentId() == null) {
                branchDTO.setSortOrder(branchRepository.getMaxIdByParentId(branchDTO.getRegionId()) + 3);
            } else {
                branchDTO.setSortOrder(branchRepository.getMaxIdByParentIdIsNull(branchDTO.getRegionId()) + 3);
            }
        }
        Branch branch = (branchMapper.toEntity(branchDTO));
        branch = branchRepository.save(branch);
        return branchMapper.toDTO(branch);
    }

    @Override
    public BranchDTO update(BranchDTO branchDTO) {
        Branch branch = new Branch();
        branch =branchRepository.save(branch);
        return branchMapper.toDTO(branch);
    }

    public List<BranchDTO> findAllByOrderBySortOrderDesc() {
        return branchMapper.toDTOS(branchRepository.findAllByOrderBySortOrderDesc());
    }

    //    @Override
//    public void deleteDepartment(Long id) {
//        Branch branch = branchRepository.getReferenceById(id);
//        branchRepository.deleteDepartment(branch);
//    }
    @Transactional
    public void delete(Long id) {
        branchRepository.updateStatus(id, CommonStatus.DELETED);
    }

    public Branch findById(Long id) {
        return branchRepository.findById(id).orElseGet(Branch::new);
    }

    public List<BranchDTO> findAllByParentId(Long parentId) {
        return branchMapper.toDTOS(branchRepository.findByParentId(parentId));
    }

    public List<BranchDTO> findBranchByStructureId(Long structureId) {
        List<Branch> branches = branchRepository.findByStructureId(structureId);
        return branchMapper.toDTOS(branches);
    }

    public StructureBranchList findAllStructureAndBranchByStructureId(Long structureId) {
        StructureBranchList structureBranchList = new StructureBranchList();
        structureBranchList.setStructureList(structureService.findAllByParentId(structureId));
        structureBranchList.setBranchList(findBranchByStructureId(structureId));
        return structureBranchList;
    }

    public List<BranchDTO> findByRegionId(Long id) {
        return branchMapper.toDTOS(branchRepository.findByRegionId(id));
    }

    public List<BranchDTO> findByStatusActive() {
        return branchMapper
                .toDTOS(branchRepository
                        .findByStatusOrderByName(CommonStatus.ACTIVE));
    }

    public List<BranchDTO> findByStatusInActive() {
        return branchMapper
                .toDTOS(branchRepository
                        .findByStatusOrderByName(CommonStatus.INACTIVE));
    }

    @Override
    public Long countBranchesByStatus() {
        return branchRepository.countStatusBranches(CommonStatus.ACTIVE);
    }
}