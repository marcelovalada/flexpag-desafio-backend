package com.flexpag.paymentscheduler.dto;

import com.flexpag.paymentscheduler.model.Bill;
import com.flexpag.paymentscheduler.model.CreditCard;
import com.flexpag.paymentscheduler.model.EnumStatus;
import com.flexpag.paymentscheduler.model.Scheduler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchedulerDTO {
    private long idScheduler;
    private LocalDate scheduleDate;
    private LocalDate currentDate = LocalDate.now();
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
}
