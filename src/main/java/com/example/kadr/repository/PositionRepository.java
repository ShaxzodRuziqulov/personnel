/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:15:09
 */
package com.example.kadr.repository;

import com.example.kadr.entity.Position;
import com.example.kadr.entity.enumitation.hr.CommonStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
    @Modifying
    @Query("update Position a set a.status = :commonStatus where a.id = :id")
    void updateStatus(@Param("id") Long id, @Param("commonStatus") CommonStatus commonStatus);

    @Query("select p from Position p where p.status='ACTIVE'order by p.sortOrder")
    List<Position> findActivePositionsOrderBySortOrder();

    @Query("select p from Position p order by p.sortOrder")
    List<Position> findByPositionsOrderBySortOrder();

    @Query("select p from Position p where p.name=:name")
    List<Position> findByName(String name);

    @Query("select p from Position p where p.status in :statuses order by p.sortOrder desc ")
    List<Position> findPositionsByStatuses(@Param("statuses") List<CommonStatus> statuses);

    @Query("select p from Position p where p.name like %:name%")
    List<Position> findPositionsByNameContaining(@Param("name") String name);

    @Query("select p from Position p where p.sortOrder between :start and :end order by p.sortOrder")
    List<Position> findPositionsBySortOrderBetween(@Param("start") Long start, @Param("end") Long end);

    @Query("select p from Position p where p.name =:name and p.status=:status")
    List<Position> findPositionsByNameAndStatus(@Param("name") String name, @Param("status") CommonStatus status);

    @Query("select p from Position p where p.sortOrder =  (Select Max(p2.sortOrder) from Position p2)")
    List<Position> findPositionsWithMaxSortOrders();

}
