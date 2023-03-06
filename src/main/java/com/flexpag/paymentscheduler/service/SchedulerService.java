package com.flexpag.paymentscheduler.service;

import com.flexpag.paymentscheduler.dto.SchedulerDTO;
import com.flexpag.paymentscheduler.model.EnumStatus;
import com.flexpag.paymentscheduler.model.Scheduler;
import com.flexpag.paymentscheduler.repository.SchedulerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@Service
public class SchedulerService {
    @Autowired
    private SchedulerRepository repository;

    public Page<SchedulerDTO> findAll(
            @RequestParam(value = "page", defaultValue = "0") String page,
            @RequestParam(value = "size", defaultValue = "5") String size) {
        Pageable paging = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));

        Page<Scheduler> scheduler = repository.findAll(paging);

        return SchedulerDTO.convert(scheduler);
    }

    public SchedulerDTO findById(long id) {
        Optional<Scheduler> scheduler = repository.findById(id);
        if (Objects.nonNull(scheduler)) {
            Scheduler scheduler2 = scheduler.get();
            return new SchedulerDTO(scheduler2);
        }
        return null;
    }

    public Page<SchedulerDTO> findByStatus(
            String status,
            @RequestParam(value = "page", defaultValue = "0") String page,
            @RequestParam(value = "size", defaultValue = "5") String size) {
        Pageable paging = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));

        if (status.equalsIgnoreCase("PAID")) {
            Page<Scheduler> scheduler = repository.findByStatusPaid(paging);

            return SchedulerDTO.convert(scheduler);
        } else if (status.equalsIgnoreCase("PENDING")) {
            Page<Scheduler> scheduler = repository.findByStatusPending(paging);

            return SchedulerDTO.convert(scheduler);
        } else {
            return null;
        }
    }

    public SchedulerDTO create(Scheduler scheduler) {
        Scheduler scheduler2 = repository.save(scheduler);
        return new SchedulerDTO(scheduler2);
    }

    public String delete(long id) {
        Optional<Scheduler> scheduler = repository.findById(id);
        if (Objects.nonNull(scheduler)) {
            SchedulerDTO schedulerDTO = new SchedulerDTO(scheduler.get());
            if (schedulerDTO.getStatus().equals(EnumStatus.PENDING)) {
                repository.deleteById(id);
                return "Schedule deleted successfully";
            } else if (schedulerDTO.getStatus().equals(EnumStatus.PAID)) {
                return "your bill has already benn paid";
            }
        }
        return "Could not delete";
    }

    public SchedulerDTO update(long id, LocalDate newDate) {
        Optional<Scheduler> scheduler = repository.findById(id);
        if (Objects.nonNull(scheduler)) {
            Scheduler schedulerNonNull = scheduler.get();

            if (schedulerNonNull.getStatus().equals(EnumStatus.PENDING)) {
                schedulerNonNull.setScheduleDate(newDate);
                repository.save(schedulerNonNull);

                return new SchedulerDTO(schedulerNonNull);
            }
        }
        return null;
    }
}
