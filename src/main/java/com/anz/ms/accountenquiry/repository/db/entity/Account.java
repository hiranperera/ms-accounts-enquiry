package com.anz.ms.accountenquiry.repository.db.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
public class Account {
    @Id
    @SequenceGenerator(name = "account_account_id_seq", sequenceName = "account_account_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_account_id_seq")
    @Column(name = "account_id", unique = true, nullable = false)
    private Long accountId;

    @Column(name = "account_number", unique = true, nullable = false)
    private String accountNumber;

    @Column(name = "account_name", nullable = false)
    private String accountName;

    @Column(name = "account_type", nullable = false)
    private Integer accountType;

    @Column(name = "balance_date")
    private Timestamp balanceDate;

    @Column(name = "currency", nullable = false)
    private Integer Currency;

    @Column(name = "opening_available_balance")
    private Double openingAvailableBalance;
}
