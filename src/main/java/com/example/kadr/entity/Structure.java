/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:11:50
 */
package com.example.kadr.entity;

import com.example.kadr.entity.enumitation.hr.CommonStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Entity
public class Structure implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long sortOrder;
    @Enumerated(EnumType.STRING)
    private CommonStatus status;
    @ManyToOne
    @JsonBackReference
    private Structure parent;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSortOrder(Long sortOrder) {
        this.sortOrder = sortOrder;
    }

    public void setParent(Structure parent) {
        this.parent = parent;
    }

    public void setStatus(CommonStatus status) {
        this.status = status;
    }
}
