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
        branch.setName(branchDTO.getName());
        branch.setStructure(structureRepository.findById(branchDTO.getStructureId()).orElseThrow(() -> new EntityNotFoundException("Structura topilmadi")));
        branch.setRegion(regionRepository.findById(branchDTO.getRegionId()).orElseThrow(() -> new EntityNotFoundException("region id topilmadi")));
        branch.setDistrict(districtRepository.findById(branchDTO.getDistrictId()).orElseThrow(() -> new EntityNotFoundException("district id topilmadi")));
        branch.setParent(setParentId(branchDTO.getParentId()));
        branch.setStatus(CommonStatus.valueOf(branchDTO.getStatus()));
        return branch;
    }

    public BranchDTO toDTO(Branch branch) {
        BranchDTO branchDTO = new BranchDTO();
        branchDTO.setId(branch.getId());
        branchDTO.setName(branch.getName());
        branchDTO.setStructureId(branch.getStructure().getId());
        branchDTO.setRegionId(branch.getRegion().getId());
        branchDTO.setDistrictId(branch.getDistrict().getId());
        branchDTO.setParentId(branch.getParent().getId());
        branchDTO.setStatus(branch.getStatus().toString());
        return branchDTO;
    }

    public List<BranchDTO> toDTOS(List<Branch> branches) {
        List<BranchDTO> resBranchDTOS = new ArrayList<>();
        for (Branch branch : branches) {
            BranchDTO branchDTO = new BranchDTO();
            branchDTO.setId(branch.getId());
            branchDTO.setName(branch.getName());
            branchDTO.setParentId(branch.getId() != null ? branch.getParent().getId() : null);
            branchDTO.setParentName(branch.getParent() != null ? branch.getParent().getName() : null);
            branchDTO.setParentName(branch.getParent().getName());
            branchDTO.setStructureId(branch.getStructure().getId());
            branchDTO.setRegionId(branch.getRegion().getId());
            branchDTO.setDistrictId(branch.getDistrict().getId());
            branchDTO.setStatus(String.valueOf(branch.getStatus()));
            resBranchDTOS.add(branchDTO);
        }

        return resBranchDTOS;
    }

    public Branch setParentId(Long parentId) {
        Branch branch = new Branch();
        branch.setId(parentId);
        return branch;
    }
}
