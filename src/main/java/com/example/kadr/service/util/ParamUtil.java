/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:23.05.2024
 * TIME:15:53
 */
package com.example.kadr.service.util;

import com.example.kadr.service.filter.PageableParam;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.awt.print.Pageable;

public final class ParamUtil {
    public static Pageable parseString(PageableParam pageableParam) {
        PageRequest pageable;
        if (!pageableParam.getSort().equals("UNSORTED")) {
            String direction = pageableParam.getSort().split(":")[1];
            Sort.Order order = new Sort.Order(Sort.Direction.valueOf(direction.trim()), pageableParam.getSort().split(":")[0]);
            pageable = PageRequest.of(pageableParam.getPageIndex(), pageableParam.getPageSize(), Sort.by(order));
        } else {
            pageable = PageRequest.of(pageableParam.getPageIndex(), pageableParam.getPageSize());
        }
        return (Pageable) pageable;
    }
}
