/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:15:20
 */
package com.example.kadr.service.impl;

import com.example.kadr.entity.enumitation.hr.CommonStatus;
import com.example.kadr.repository.StructureRepository;
import com.example.kadr.entity.Structure;
import com.example.kadr.service.BranchService;
import com.example.kadr.service.StructureService;
import com.example.kadr.service.dto.StructureBranchList;
import com.example.kadr.service.dto.StructureDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StructureServiceImpl implements StructureService {
    private final Logger log = LoggerFactory.getLogger(BranchService.class);
    private final StructureRepository structureRepository;

    public StructureServiceImpl(StructureRepository structureRepository) {
        this.structureRepository = structureRepository;
    }


    public String create(StructureDTO structureDTO) {
        try {
            structureRepository.save(toEntity(structureDTO));
            return "Muvaffaqiyatli saqlandi";
        } catch (Exception e) {
            e.printStackTrace();
            return "Xatolik";
        }
    }

    public Structure setParentId(Long parentId) {
        Structure structure = new Structure();
        structure.setId(parentId);
        return structure;
    }

    public String update(StructureDTO structureDTO) {
        try {
            if (structureDTO.getId() != null) {
                if (structureRepository.findById(structureDTO.getId()).isPresent()) {
                    Structure curentstructure = structureRepository.findById(structureDTO.getId()).get();
                    curentstructure.setName(structureDTO.getName());
                    curentstructure.setSortOrder(structureDTO.getSortOrder());
                    curentstructure.setParent(setParentId(structureDTO.getParentId()));
                    structureRepository.save(curentstructure);
                    return "Muvaffaqiyatli uzgartirildi";
                } else {
                    return "Id topilmadi";
                }
            } else {
                return "Xatolik: id null ga teng";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Xatolik";
        }

    }

    public String updateNew(StructureDTO structureDTO) {
        try {
            structureRepository.save(toEntity(structureDTO));
            return "Muvaffaqiyatli uzgartirildi";
        } catch (
                Exception e) {
            e.printStackTrace();
            return "Xatolik";
        }

    }

    public List<StructureDTO> all() {
        return toDTOS(structureRepository.findAll());
    }

    public List<StructureDTO> findAllByOrderBySortOrderAsc() {
        return toDTOS(structureRepository.findAllByOrderBySortOrderAsc());
    }

    public void delete(Long id) {
        log.debug("Request to delete Branch : {}", id);
        structureRepository.updateStatus(id, CommonStatus.DELETED);
    }

    @Override
    public List<StructureDTO> findAllByParentId(Long parentId) {
        return toDTOS(structureRepository.findAllByParentId(parentId));
    }

    public Structure findById(Long id) {
        Optional<Structure> optional = structureRepository.findById(id);
        return optional.orElseGet(Structure::new);
    }

    public StructureDTO toDto(Structure structure) {
        StructureDTO structureDTO = new StructureDTO();
        structureDTO.setId(structure.getId());
        structureDTO.setName(structure.getName());
        structureDTO.setSortOrder(structure.getSortOrder());
        structureDTO.setParentId(structure.getParent() != null ? structure.getParent().getId() : null);
        structureDTO.setParentName(structure.getParent() != null ? structure.getParent().getName() : null);
        return structureDTO;
    }

    public Structure toEntity(StructureDTO structureDTO) {
        Structure structure = new Structure();
        structure.setId(structureDTO.getId());
        structure.setName(structureDTO.getName());
        structure.setStatus(CommonStatus.valueOf(structureDTO.getStatus()));
        structure.setSortOrder(structureDTO.getSortOrder());
        structure.setParent(setParentId(structureDTO.getParentId()));
        return structure;
    }

    public List<StructureDTO> toDTOS(List<Structure> structures) {
        List<StructureDTO> resStructureDTOS = new ArrayList<>();
        for (Structure structure : structures) {
            StructureDTO structureDTO = new StructureDTO();
            structureDTO.setId(structure.getId());
            structureDTO.setName(structure.getName());
            structureDTO.setSortOrder(structure.getSortOrder());
            structureDTO.setParentId(structure.getParent() != null ? structure.getParent().getId() : null);
            structureDTO.setParentName(structure.getParent() != null ? structure.getParent().getName() : null);
            structureDTO.setStatus(String.valueOf(structure.getStatus()));
            resStructureDTOS.add(structureDTO);
        }

        return resStructureDTOS;
    }


}
