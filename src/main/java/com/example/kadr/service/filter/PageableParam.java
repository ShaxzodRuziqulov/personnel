/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:23.05.2024
 * TIME:15:48
 */
package com.example.kadr.service.filter;

import lombok.Data;
import org.springframework.data.domain.Sort;
@Data
public class PageableParam {
private int pageIndex;
private int pageSize;
private String sort;
private Sort.Direction direction;
}
