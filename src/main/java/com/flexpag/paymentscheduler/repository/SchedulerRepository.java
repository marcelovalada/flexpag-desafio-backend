package com.flexpag.paymentscheduler.repository;

import com.flexpag.paymentscheduler.model.Scheduler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SchedulerRepository extends JpaRepository<Scheduler, Long> {

    @Query(value = "SELECT * FROM TB_SCHEDULER WHERE STATUS= 'PAID'", nativeQuery = true)
    Page<Scheduler> findByStatusPaid(Pageable pageable);

    @Query(value = "SELECT * FROM TB_SCHEDULER WHERE STATUS= 'PENDING'", nativeQuery = true)
    Page<Scheduler> findByStatusPending(Pageable pageable);
}
