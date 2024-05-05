/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:05.05.2024
 * TIME:13:18
 */
package com.example.kadr.service.mapper.mp;

import com.example.kadr.entity.Structure;
import com.example.kadr.service.dto.StructureDTO;
import com.example.kadr.service.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StructureMapper extends EntityMapper<StructureDTO, Structure> {
    @Mapping(source = "parent_id", target = "parentId")
    StructureDTO toDo(Structure s);

    @Override
    @Mapping(source = "parentId", target = "parent")
    Structure toEntity(StructureDTO structureDTO);

    default Structure fromId(Long id) {
        if (id == null) {
            return null;
        }
        Structure structure = new Structure();
        structure.setId(id);
        return structure;
    }
}