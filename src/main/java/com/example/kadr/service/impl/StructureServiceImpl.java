/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:15:20
 */
package com.example.kadr.service.impl;

import com.example.kadr.repository.StructureRepository;
import com.example.kadr.entity.Structure;
import com.example.kadr.service.StructureService;
import com.example.kadr.service.dto.StructureDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StructureServiceImpl implements StructureService {
    private final StructureRepository structureRepository;

    public StructureServiceImpl(StructureRepository structureRepository) {
        this.structureRepository = structureRepository;
    }

    public String create(StructureDTO structureDTO) {
        try {
            Structure structure = new Structure();
            structure.setName(structureDTO.getName());
            structure.setSortOrder(structureDTO.getSortOrder());
            structure.setParent(setParentId(structureDTO.getParentId()));
            structureRepository.save(structure);
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

    public StructureDTO toDto(Structure structure) {
        StructureDTO structureDTO = new StructureDTO();
        structureDTO.setId(structure.getId());
        structureDTO.setName(structure.getName());
        structureDTO.setSortOrder(structure.getSortOrder());
        structureDTO.setParentId(structure.getParent() != null ? structure.getParent().getId() : null);
        structureDTO.setParentName(structure.getParent() != null ? structure.getParent().getName() : null);
        return structureDTO;
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
            resStructureDTOS.add(structureDTO);
        }

        return resStructureDTOS;
    }

    public List<StructureDTO> all() {
        return toDTOS(structureRepository.findAll());
    }

    public void delete(Long id) {
        Structure delete = structureRepository.getReferenceById(id);
        structureRepository.delete(delete);
    }

    @Override
    public List<StructureDTO> findAllByParentId(Long parentId) {
        return toDTOS(structureRepository.findAllByParentId(parentId));
    }

    public Structure findById(Long id) {
        Optional<Structure> optional = structureRepository.findById(id);
        return optional.orElseGet(Structure::new);
    }


}
