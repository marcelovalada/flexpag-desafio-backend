package com.flexpag.paymentscheduler.dto;

import com.flexpag.paymentscheduler.model.Bill;
import com.flexpag.paymentscheduler.model.CreditCard;
import com.flexpag.paymentscheduler.model.EnumStatus;
import com.flexpag.paymentscheduler.model.Scheduler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchedulerDTO {
    private long idScheduler;
    private LocalDateTime scheduleDate;
    private EnumStatus status;
    private Bill bill;
    private CreditCard creditCard;

    public SchedulerDTO(Scheduler scheduler){
        this.idScheduler=scheduler.getIdScheduler();
        this.bill=scheduler.getBill();
        this.creditCard=scheduler.getCreditCard();
        this.scheduleDate=scheduler.getScheduleDate();
        this.status=scheduler.getStatus();
    }

    public static Page<SchedulerDTO> convert(Page<Scheduler> scheduler){
        return scheduler.map(SchedulerDTO::new);
    }
}
