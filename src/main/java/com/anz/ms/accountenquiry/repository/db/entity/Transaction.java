package com.anz.ms.accountenquiry.repository.db.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
public class Transaction {
    @Id
    @SequenceGenerator(name = "transaction_transaction_id_seq", sequenceName = "transaction_transaction_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_transaction_id_seq")
    @Column(name = "transaction_id", unique = true, nullable = false)
    private Long transactionId;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "value_date")
    private Timestamp valueDate;

    @Column(name = "currency", nullable = false)
    private Integer currency;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "type", nullable = false)
    private Integer transactionType;

    @Column(name = "narrative")
    private String transactionNarrative;
}
