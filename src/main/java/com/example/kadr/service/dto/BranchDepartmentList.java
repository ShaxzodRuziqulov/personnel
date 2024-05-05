/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:05.05.2024
 * TIME:18:03
 */
package com.example.kadr.service.dto;

import java.util.List;

public class BranchDepartmentList {
    List<BranchDTO> branchDTOList;
    List<DepartmentDTO> departmentDTOList;

    public List<BranchDTO> getBranchDTOList() {
        return branchDTOList;
    }

    public void setBranchDTOList(List<BranchDTO> branchDTOList) {
        this.branchDTOList = branchDTOList;
    }

    public List<DepartmentDTO> getDepartmentDTOList() {
        return departmentDTOList;
    }

    public void setDepartmentDTOList(List<DepartmentDTO> departmentDTOList) {
        this.departmentDTOList = departmentDTOList;
    }
}
