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
    public String create(BranchDTO branchDTO) {
        try {
            branchRepository.save(branchMapper.toEntity(branchDTO));
            return "Muvafiqiyatli saqlandi";
        } catch (Exception e) {
            e.printStackTrace();
            return "xatolik";
        }
    }

    @Override
    public String update(BranchDTO branchDTO) {
        try {
            branchRepository.save(branchMapper.toEntity(branchDTO));
            return "Muvaffaqiyatli uzgartirildi";
        } catch (Exception e) {
            e.printStackTrace();
            return "Xatolik";
        }
    }

    public List<BranchDTO> all() {
        return branchMapper.toDTOS(branchRepository.findAll());
    }

    //    @Override
//    public void delete(Long id) {
//        Branch branch = branchRepository.getReferenceById(id);
//        branchRepository.delete(branch);
//    }

    public void delete(Long id) {
        log.debug("Request to delete Branch : {}", id);
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
}