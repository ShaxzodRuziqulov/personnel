/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:11:51
 */
package com.example.kadr.entity;

import com.example.kadr.entity.enumitation.hr.CommonStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long sortOrder;
    @Enumerated(EnumType.STRING)
    private CommonStatus status;
    @ManyToOne
    @JsonBackReference
    private Branch parent;
    @ManyToOne
    @JsonBackReference
    private Structure structure;
    @ManyToOne
    private Region region;
    @ManyToOne
    private District district;
}
