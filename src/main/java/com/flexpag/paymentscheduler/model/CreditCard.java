package com.flexpag.paymentscheduler.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_credit_card")
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCreditCard;
    @Column(nullable = false)
    private long cardNumber;
    @Column(nullable = false)
    private int expirationMonth;
    @Column(nullable = false)
    private int expirationYear;
    @Column(nullable = false)
    private String holderName;
    @Column(nullable = false)
    private long cvv;

}
