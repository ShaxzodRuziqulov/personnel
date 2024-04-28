/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:11:52
 */
package com.example.kadr.entity;

import com.example.kadr.entity.enumitation.hr.CommonStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    private Branch branch;
    @Enumerated(EnumType.STRING)
    private CommonStatus status;
}
