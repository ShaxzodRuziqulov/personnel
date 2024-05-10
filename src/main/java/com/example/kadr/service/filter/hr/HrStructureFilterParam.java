/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:23.05.2024
 * TIME:15:45
 */
package com.example.kadr.service.filter.hr;

import com.example.kadr.entity.enumitation.hr.CommonStatus;
import com.example.kadr.service.filter.PageableParam;
import lombok.Data;

@Data
public class HrStructureFilterParam {
    private Long id;
    private String name;
    private long sortOrder;
    private CommonStatus status;
    private Long parentId;
    private PageableParam pageable;
}
