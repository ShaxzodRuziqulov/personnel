/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:22.04.2024
 * TIME:22:27
 */
package com.example.kadr.web.rest;

import com.example.kadr.entity.Branch;
import com.example.kadr.entity.Job;
import com.example.kadr.entity.request.ReqBranch;
import com.example.kadr.entity.request.ReqJob;
import com.example.kadr.service.JobService;
import com.example.kadr.service.dto.JobDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job")
public class JobResource {
    private final JobService jobService;

    public JobResource(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody JobDTO jobDTO) {
        String response = jobService.create(jobDTO);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody JobDTO jobDTO) {
        String response = jobService.update(jobDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<?> all() {
        List<JobDTO> branches = jobService.all();
        return ResponseEntity.ok(branches);
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Job job = jobService.findById(id);
        return ResponseEntity.ok(job);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        jobService.delete(id);
        return ResponseEntity.ok("o'chirildi");
    }
}
