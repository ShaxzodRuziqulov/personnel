/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:17:25
 */
package com.example.kadr.service.filter.hr;

import lombok.Data;

@Data
public class ReqJob {
    private Long id;
    private String name;
    private Long departmentId;
    private Long positionId;
}
