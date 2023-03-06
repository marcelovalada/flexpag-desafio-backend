package com.flexpag.paymentscheduler.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idBill;
    @Column(nullable = false)
    private LocalDate dateOfExpiration;
    @Column(nullable = false)
    private Long billValue;
}
