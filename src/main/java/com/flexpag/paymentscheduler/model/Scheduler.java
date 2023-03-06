package com.flexpag.paymentscheduler.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_scheduler")
public class Scheduler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idScheduler;
    @Column(nullable = false)
    private LocalDateTime scheduleDate;
    @Enumerated(EnumType.STRING)
    private EnumStatus status = EnumStatus.PENDING;
    @OneToOne
    @JoinColumn(name = "idBill")
    private Bill bill;

    @OneToOne
    @JoinColumn(name = "idCreditCard")
    private CreditCard creditCard;
}
