/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:11:55
 */
package com.example.kadr.entity;

import com.example.kadr.entity.enumitation.EmployeeStatus;
import com.example.kadr.entity.enumitation.Gender;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String dateBirth;
    private EmployeeStatus status;
    @ManyToOne
    private Job job;
    private String userName;
    private Gender gender;
    private String hashId;
}
