/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:15:44
 */
package com.example.kadr.service.filter.hr;

import lombok.Data;

@Data
public class ReqBranch {
    private Long id;
    private String name;
    private Long structureId;
    private Long parentId;
}
