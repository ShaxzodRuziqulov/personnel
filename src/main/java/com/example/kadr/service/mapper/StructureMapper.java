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
import java.util.stream.Collectors;

@Component
public class StructureMapper {

    public StructureDTO toDTO(Structure structure) {
        if (structure == null) {
            return null;
        }
        StructureDTO structureDTO = new StructureDTO();
        structureDTO.setId(structure.getId());
        structureDTO.setName(structure.getName());
        structureDTO.setSortOrder(structure.getSortOrder());

        Structure parent = structure.getParent();
        if (parent != null) {
            structureDTO.setParentId(parent.getId());
            structureDTO.setParentName(parent.getName());
        }
        structureDTO.setStatus((structure.getStatus().toString()));
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
        return structures.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Structure setParentId(Long parentId) {
        Structure structure = new Structure();
        structure.setId(parentId);
        return structure;
    }
}