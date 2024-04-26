/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:19:28
 */
package com.example.kadr.entity.request;

import com.example.kadr.entity.enumitation.EmployeeStatus;
import lombok.Data;

@Data
public class ReqEmployee {
    private Long id;
    private String firstName;
    private String lastName;
    private String dateBirth;
    private EmployeeStatus status;
    private Long jobId;
    private String username;
    private int gender;
    private String hashId;

}
