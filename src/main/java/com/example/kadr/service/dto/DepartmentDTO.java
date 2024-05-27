/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:16:18
 */
package com.example.kadr.service.dto;

import lombok.Data;

@Data
public class DepartmentDTO {
    private Long id;
    private Long sortOrder;
    private String name;
    private Long branchId;
    private String status;


}