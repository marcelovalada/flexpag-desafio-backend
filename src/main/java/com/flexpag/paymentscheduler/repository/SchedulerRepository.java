package com.flexpag.paymentscheduler.repository;

import com.flexpag.paymentscheduler.model.Scheduler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchedulerRepository extends JpaRepository<Scheduler, Long> {

    Page<Scheduler> findByStatusPaid(Pageable paging);
}
