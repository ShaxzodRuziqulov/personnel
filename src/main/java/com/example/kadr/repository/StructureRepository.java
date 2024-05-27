/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:15:01
 */
package com.example.kadr.repository;

import com.example.kadr.entity.Structure;
import com.example.kadr.entity.enumitation.hr.CommonStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StructureRepository extends JpaRepository<Structure, Long> {
    List<Structure> findAllByOrderBySortOrderDesc();

    List<Structure> findAllByParentId(Long parentId);

    List<Structure> findAllByParentIsNullAndStatusOrderBySortOrder(CommonStatus status);

    List<Structure> findAllByParentIdOrderBySortOrderAsc(Long parentId);

    List<Structure> findAllByStatusOrderBySortOrderAsc(CommonStatus status);

//    @Query("SELECT s FROM Structure s WHERE "
//            + "(:id IS NULL OR s.id = :id) AND "
//            + "(:name IS NULL OR s.name = :name) AND "
//            + "(:sortOrder IS NULL OR s.sortOrder = :sortOrder) AND "
//            + "(:status IS NULL OR s.status = :status) AND "
//            + "(:parentId IS NULL OR s.parent.id = :parentId)")
//    Page<Structure> findAllPaging(
//            @Param("id") Long id,
//            @Param("name") String name,
//            @Param("sortOrder") Long sortOrder,
//            @Param("status") CommonStatus status,
//            @Param("parentId") Long parentId,
//            Pageable pageable
//    );

    @Transactional
    @Modifying
    @Query("update Structure a set a.status = :commonStatus where a.id = :id")
    void updateStatus(@Param("id") Long id, @Param("commonStatus") CommonStatus commonStatus);
    @Query("select coalesce(max (b.sortOrder),0) from Structure b")
    Long getMaxSortOrder();

}
