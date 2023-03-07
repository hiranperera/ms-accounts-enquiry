package com.anz.ms.accountenquiry.repository.db.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id", unique = true, nullable = false)
    private Long transactionId;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "value_date")
    private Timestamp valueDate;

    @ManyToOne
    @JoinColumn(name = "currency_id", nullable = false)
    private Currency currency;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "type", nullable = false)
    private Integer transactionType;

    @Column(name = "narrative")
    private String transactionNarrative;
}
