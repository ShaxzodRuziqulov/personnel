/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:05.05.2024
 * TIME:16:53
 */
package com.example.kadr.service.dto;

import java.util.List;

public class StructureBranchList {
    private List<StructureDTO> structureList;
    private List<BranchDTO> branchList;

    public List<StructureDTO> getStructureList() {
        return structureList;
    }

    public List<BranchDTO> getBranchList() {
        return branchList;
    }

    public void setStructureList(List<StructureDTO> structureList) {
        this.structureList = structureList;
    }

    public void setBranchList(List<BranchDTO> branchList) {
        this.branchList = branchList;
    }
}
