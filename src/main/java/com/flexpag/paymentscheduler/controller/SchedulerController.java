package com.flexpag.paymentscheduler.controller;

import com.flexpag.paymentscheduler.dto.SchedulerDTO;
import com.flexpag.paymentscheduler.model.Scheduler;
import com.flexpag.paymentscheduler.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/scheduler")
public class SchedulerController {
    @Autowired
    private SchedulerService service;

    @GetMapping
    public ResponseEntity<Page<SchedulerDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") String page,
            @RequestParam(value = "size", defaultValue = "5") String size
    ){
        return ResponseEntity.ok().body(service.findAll(page, size));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SchedulerDTO> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping(value = "/status/{status}")
    public ResponseEntity<Page<SchedulerDTO>> findByStatus(
            @PathVariable String status,
            @RequestParam(value = "page", defaultValue = "0") String page,
            @RequestParam(value = "size", defaultValue = "5") String size
    ){
        return ResponseEntity.ok().body(service.findByStatus(status, page, size));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Scheduler scheduler){
        SchedulerDTO schedulerDTO = service.create(scheduler);
        return ResponseEntity.created(null).body(schedulerDTO.getIdScheduler());
    }

    @PutMapping("/{id}/{newDate}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @PathVariable("newDate") LocalDateTime newDate){
        return ResponseEntity.ok().body(service.updateDate(id, newDate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(service.delete(id));
    }

}
