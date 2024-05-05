/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:05.05.2024
 * TIME:18:08
 */
package com.example.kadr.service.dto;

import java.util.List;

public class DepartmentJobList {
    private List<DepartmentDTO> departmentDTOList;
    private List<JobDTO> jobDTOList;

    public List<DepartmentDTO> getDepartmentDTOList() {
        return departmentDTOList;
    }

    public void setDepartmentDTOList(List<DepartmentDTO> departmentDTOList) {
        this.departmentDTOList = departmentDTOList;
    }

    public List<JobDTO> getJobDTOList() {
        return jobDTOList;
    }

    public void setJobDTOList(List<JobDTO> jobDTOList) {
        this.jobDTOList = jobDTOList;
    }
}
