/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:22:27
 */
package com.example.kadr.web.rest;

import com.example.kadr.entity.Job;
import com.example.kadr.service.JobService;
import com.example.kadr.service.dto.DepartmentJobList;
import com.example.kadr.service.dto.JobDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class JobResource {
    private final JobService jobService;

    public JobResource(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping("/jobs/create")
    public ResponseEntity<?> create(@RequestBody JobDTO jobDTO) throws URISyntaxException {
        JobDTO result = jobService.create(jobDTO);
        return ResponseEntity.created(new URI("api/jobs/create")).body(result);
    }

    @PutMapping("/jobs/update/{id}")
    public ResponseEntity<?> update(@RequestBody JobDTO jobDTO, @PathVariable Long id) {
        if (jobDTO.getId() == null || !jobDTO.getId().equals(id)) {
            return ResponseEntity.badRequest().body("Invalid Id");
        }
        try {
            JobDTO result = jobService.update(jobDTO);
            return ResponseEntity.ok(result);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/jobs/all")
    public ResponseEntity<?> all() {
        List<JobDTO> branches = jobService.all();
        return ResponseEntity.ok(branches);
    }

    @GetMapping("/jobs/by/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Job job = jobService.findById(id);
        return ResponseEntity.ok(job);
    }

    @GetMapping("/jobs/by-departmentId/{id}")
    public ResponseEntity<?> findByDepartmentId(@PathVariable Long id) {
        List<JobDTO> job = jobService.findAllJobByDepartmentId(id);
        return ResponseEntity.ok(job);
    }

    @GetMapping("/jobs/by-departmentId/list/{departmentId}")
    public ResponseEntity<?> findDepartmentByJob(@PathVariable Long departmentId) {
        DepartmentJobList job = jobService.findAllDepartmentByJobId(departmentId);
        return ResponseEntity.ok(job);
    }

    @DeleteMapping("/jobs/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        jobService.delete(id);
        return ResponseEntity.ok("o'chirildi");
    }

    @GetMapping("/jobs/by-status/active")
    public ResponseEntity<?> findByStatusActive() {
        List<JobDTO> statusActive = jobService.findByStatusActive();
        return ResponseEntity.ok(statusActive);
    }

    @GetMapping("/jobs/by-status/inactive")
    public ResponseEntity<?> findByStatusInActive() {
        List<JobDTO> statusInactive = jobService.findByStatusInactive();
        return ResponseEntity.ok(statusInactive);
    }

    @GetMapping("/jobs/by-position/id/{jobId}")
    public ResponseEntity<?> findByPositionIdOrderById(@PathVariable Long jobId) {
        Long findByPositionIdOrderById = jobService.findByPositionIdOrderById(jobId);
        return ResponseEntity.ok(findByPositionIdOrderById);
    }

    @GetMapping("/jobs/by-sortOrder/{sortOrder}")
    public ResponseEntity<?> findBySortOrderGreaterThan(@PathVariable Long sortOrder) {
        List<JobDTO> findBySortOrderGreaterThan = jobService.findBySortOrderGreaterThan(sortOrder);
        return ResponseEntity.ok(findBySortOrderGreaterThan);
    }

    @GetMapping("/jobs/by/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name) {
        List<JobDTO> findByName = jobService.findByName(name);
        return ResponseEntity.ok(findByName);
    }

    @GetMapping("/jobs/by-position/{positionId}")
    public ResponseEntity<?> findByPositionId(@PathVariable Long positionId) {
        List<JobDTO> findByPositionId = jobService.findByPositionId(positionId);
        return ResponseEntity.ok(findByPositionId);
    }

    @GetMapping("/jobs/by-department/{departmentId}")
    public ResponseEntity<?> findDepartmentStatus(@PathVariable Long departmentId) {
        List<JobDTO> findDepartmentStatus = jobService.findByDepartmentIdAndStatus(departmentId);
        return ResponseEntity.ok(findDepartmentStatus);
    }

    @GetMapping("/jobs/by-position/sortOrder/{positionId}/{sortOrder}")
    public ResponseEntity<List<JobDTO>> findByPositionIdAndSortOrderGreaterThan(@PathVariable Long positionId, @PathVariable Long sortOrder) {
        List<JobDTO> jobs = jobService.findByPositionIdAndSortOrderGreaterThan(positionId, sortOrder);
        return ResponseEntity.ok(jobs);
    }

    @GetMapping("/jobs/by-sort-order")
    public ResponseEntity<?> findAllOrderBySortOrderAsc() {
        List<JobDTO> findAllOrderBySortOrderAsc = jobService.findAllOrderBySortOrderAsc();
        return ResponseEntity.ok(findAllOrderBySortOrderAsc);
    }

    @GetMapping("/jobs/by-department/name/{name}/{departmentId}")
    public ResponseEntity<?> findByNameAndDepartmentId(@PathVariable String name, @PathVariable Long departmentId) {
        List<JobDTO> findByNameAndDepartmentId = jobService.findByNameAndDepartmentId(name, departmentId);
        return ResponseEntity.ok(findByNameAndDepartmentId);
    }
}
