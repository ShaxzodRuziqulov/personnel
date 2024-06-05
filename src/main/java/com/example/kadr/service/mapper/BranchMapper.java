/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:05.05.2024
 * TIME:13:15
 */
package com.example.kadr.service.mapper;

import com.example.kadr.entity.Branch;
import com.example.kadr.entity.enumitation.hr.CommonStatus;
import com.example.kadr.repository.DistrictRepository;
import com.example.kadr.repository.RegionRepository;
import com.example.kadr.repository.StructureRepository;
import com.example.kadr.service.dto.BranchDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BranchMapper {
    private final StructureRepository structureRepository;
    private final RegionRepository regionRepository;
    private final DistrictRepository districtRepository;


    public BranchMapper(StructureRepository structureRepository, RegionRepository regionRepository, DistrictRepository districtRepository) {

        this.structureRepository = structureRepository;
        this.regionRepository = regionRepository;
        this.districtRepository = districtRepository;
    }

    public Branch toEntity(BranchDTO branchDTO) {
        Branch branch = new Branch();
        branch.setId(branchDTO.getId());
        branch.setSortOrder(branchDTO.getSortOrder());
        branch.setName(branchDTO.getName());
        branch.setStructure(structureRepository.findById(branchDTO.getStructureId()).orElseThrow(() -> new EntityNotFoundException("Structura topilmadi")));
        branch.setRegion(regionRepository.findById(branchDTO.getRegionId()).orElseThrow(() -> new EntityNotFoundException("region id topilmadi")));
        branch.setDistrict(districtRepository.findById(branchDTO.getDistrictId()).orElseThrow(() -> new EntityNotFoundException("district id topilmadi")));
        branch.setParent(setParentId(branchDTO.getParentId()));
        branch.setStatus(CommonStatus.valueOf(branchDTO.getStatus()));
        return branch;
    }

    public BranchDTO toDTO(Branch branch) {
        if (branch == null) {
            return null;
        }
        BranchDTO branchDTO = new BranchDTO();
        branchDTO.setId(branch.getId());
        branchDTO.setSortOrder(branch.getSortOrder());
        branchDTO.setName(branch.getName());
        branchDTO.setStructureId(branch.getStructure().getId());
        branchDTO.setRegionId(branch.getRegion().getId());
        branchDTO.setDistrictId(branch.getDistrict().getId());
        Branch parent = branch.getParent();
        if (parent != null) {
            branchDTO.setParentId(branch.getParent().getId());
            branchDTO.setParentName(branch.getParent().getName());
        }
        branchDTO.setStatus(branch.getStatus().toString());
        return branchDTO;
    }

    public List<BranchDTO> toDTOS(List<Branch> branches) {
        return branches.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Branch setParentId(Long parentId) {
        Branch branch = new Branch();
        branch.setId(parentId);
        return branch;
    }
}
