/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:15:41
 */
package com.example.kadr.service.impl;

import com.example.kadr.entity.enumitation.hr.CommonStatus;
import com.example.kadr.repository.BranchRepository;
import com.example.kadr.repository.DistrictRepository;
import com.example.kadr.repository.RegionRepository;
import com.example.kadr.repository.StructureRepository;
import com.example.kadr.entity.Branch;
import com.example.kadr.service.BranchService;
import com.example.kadr.service.dto.BranchDTO;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BranchServiceImpl implements BranchService {
    private final Logger log = LoggerFactory.getLogger(BranchService.class);
    private final BranchRepository branchRepository;
    private final RegionRepository regionRepository;
    private final StructureRepository structureRepository;
    private final DistrictRepository districtRepository;

    public BranchServiceImpl(BranchRepository branchRepository, RegionRepository regionRepository, StructureRepository structureRepository, DistrictRepository districtRepository) {
        this.branchRepository = branchRepository;
        this.regionRepository = regionRepository;
        this.structureRepository = structureRepository;
        this.districtRepository = districtRepository;
    }

    @Override
    public String create(BranchDTO branchDTO) {
        try {
            branchRepository.save(toEntity(branchDTO));
            return "Muvafiqiyatli saqlandi";
        } catch (Exception e) {
            e.printStackTrace();
            return "xatolik";
        }
    }

    public Branch setParentId(Long parentId) {
        Branch branch = new Branch();
        branch.setId(parentId);
        return branch;
    }

    @Override
    public String update(BranchDTO branchDTO) {
        try {
            branchRepository.save(toEntity(branchDTO));
            return "Muvaffaqiyatli uzgartirildi";
        } catch (Exception e) {
            e.printStackTrace();
            return "Xatolik";
        }
    }

    public List<BranchDTO> all() {
        return toDTOS(branchRepository.findAll());
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

    public List<Branch> findAllByParentId(Long parentId) {
        return branchRepository.findByParentId(parentId);
    }

    public List<Branch> findByStructureId(Long structureId) {
        return branchRepository.findByStructureId(structureId);
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

}
