/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:19:28
 */
package com.example.kadr.service.dto;

import com.example.kadr.entity.enumitation.Gender;
import com.example.kadr.entity.enumitation.hr.CommonStatus;
import lombok.Getter;

@Getter
public class EmployeeDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String dateBirth;
    private String status;
    private Long jobId;
    private String username;
    private Gender gender;
    private String hashId;

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateBirth(String dateBirth) {
        this.dateBirth = dateBirth;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setHashId(String hashId) {
        this.hashId = hashId;
    }
}
