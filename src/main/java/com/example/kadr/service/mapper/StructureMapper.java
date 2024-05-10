/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:05.05.2024
 * TIME:13:18
 */
package com.example.kadr.service.mapper;

import com.example.kadr.entity.Structure;
import com.example.kadr.entity.enumitation.hr.CommonStatus;
import com.example.kadr.service.dto.StructureDTO;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StructureMapper {

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

    public Structure setParentId(Long parentId) {
        Structure structure = new Structure();
        structure.setId(parentId);
        return structure;
    }
}