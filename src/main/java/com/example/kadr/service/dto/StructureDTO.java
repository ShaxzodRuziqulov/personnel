/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:15:13
 */
package com.example.kadr.service.dto;


import lombok.Getter;

@Getter
public class StructureDTO {
    private Long id;
    private String name;
    private Long sortOrder;
    private Long parentId;
    private String status;
    private String parentName;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSortOrder(Long sortOrder) {
        this.sortOrder = sortOrder;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
