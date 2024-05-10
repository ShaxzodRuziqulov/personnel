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
import com.example.kadr.service.dto.StructureDTO;
import com.example.kadr.service.mapper.StructureMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StructureServiceImpl implements StructureService {
    private final Logger log = LoggerFactory.getLogger(BranchService.class);
    private final StructureRepository structureRepository;
    private final StructureMapper structureMapper;

    public StructureServiceImpl(StructureRepository structureRepository, StructureMapper structureMapper) {
        this.structureRepository = structureRepository;
        this.structureMapper = structureMapper;
    }


    public String create(StructureDTO structureDTO) {
        try {
            structureRepository.save(structureMapper.toEntity(structureDTO));
            return "Muvaffaqiyatli saqlandi";
        } catch (Exception e) {
            e.printStackTrace();
            return "Xatolik";
        }
    }


//    public String update(StructureDTO structureDTO) {
//        try {
//            if (structureDTO.getId() != null) {
//                if (structureRepository.findById(structureDTO.getId()).isPresent()) {
//                    Structure curentstructure = structureRepository.findById(structureDTO.getId()).get();
//                    curentstructure.setName(structureDTO.getName());
//                    curentstructure.setSortOrder(structureDTO.getSortOrder());
//                    curentstructure.setParent(setParentId(structureDTO.getParentId()));
//                    structureRepository.save(curentstructure);
//                    return "Muvaffaqiyatli uzgartirildi";
//                } else {
//                    return "Id topilmadi";
//                }
//            } else {
//                return "Xatolik: id null ga teng";
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "Xatolik";
//        }
//
//    }

    public String updateNew(StructureDTO structureDTO) {
        try {
            structureRepository.save(structureMapper.toEntity(structureDTO));
            return "Muvaffaqiyatli uzgartirildi";
        } catch (
                Exception e) {
            e.printStackTrace();
            return "Xatolik";
        }

    }

    public List<StructureDTO> all() {
        return structureMapper.toDTOS(structureRepository.findAll());
    }

    public List<StructureDTO> findAllByOrderBySortOrderDesc() {
        return structureMapper.toDTOS(structureRepository.findAllByOrderBySortOrderDesc());
    }

    public void delete(Long id) {
        log.debug("Request to delete Branch : {}", id);
        structureRepository.updateStatus(id, CommonStatus.DELETED);
    }

    @Override
    public List<StructureDTO> findAllByParentId(Long parentId) {
        return structureMapper.toDTOS(structureRepository.findAllByParentId(parentId));
    }

    public Structure findById(Long id) {
        Optional<Structure> optional = structureRepository.findById(id);
        return optional.orElseGet(Structure::new);
    }

    public List<StructureDTO> findAllByParentIsNull() {
        return structureMapper.toDTOS(structureRepository.findAllByParentIsNullAndStatusOrderBySortOrder(CommonStatus.ACTIVE));
    }

    public List<StructureDTO> findAllHrStructureList(Long parentId) {
        return structureMapper.toDTOS(structureRepository.findAllByParentIdOrderBySortOrderAsc(parentId));
    }

    public List<StructureDTO> findAllList() {
        return structureMapper.toDTOS
                (structureRepository.findAllByStatusOrderBySortOrderAsc
                (CommonStatus.ACTIVE));
    }

//    public Page<StructureDTO> findAllPaging(
//            Pageable pageable,
//            Long id,
//            String name,
//            Long sortOrder,
//            CommonStatus status,
//            Long parentId) {
//        Page<Structure> structurePage = structureRepository.findAllPaging(
//                id, name, sortOrder, status, parentId,pageable);
//        return structurePage.map(structureMapper::toDto);
//    }


}
