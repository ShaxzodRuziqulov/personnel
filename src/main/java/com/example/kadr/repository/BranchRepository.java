/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:15:07
 */
package com.example.kadr.repository;

import com.example.kadr.entity.Branch;
import com.example.kadr.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<Branch,Long> {
    List<Branch> findByParentId(Long parentId);
    List<Branch> findByRegionId(Long regionId);
}
