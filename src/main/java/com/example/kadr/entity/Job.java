/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:11:53
 */
package com.example.kadr.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    private Department department;
    @ManyToOne
    private Position position;
}
