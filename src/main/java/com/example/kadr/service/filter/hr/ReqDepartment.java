/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:16:18
 */
package com.example.kadr.service.filter.hr;

import lombok.Data;

@Data
public class ReqDepartment {
    private Long id;
    private String name;
    private Long branchId;
}