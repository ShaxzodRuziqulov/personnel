/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:24.04.2024
 * TIME:22:40
 */
package com.example.kadr.entity;

import com.example.kadr.entity.enumitation.hr.CommonStatus;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class District {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Long sortOrder;
    @Enumerated(EnumType.STRING)
    private CommonStatus status;
}
