package com.anz.ms.accountenquiry.repository.db.entity;

import lombok.Getter;

@Getter
public enum TransactionType {

    CREDIT ("Credit"),
    DEBIT ("Debit");

    private final String name;

    TransactionType(String name) {
        this.name = name;
    }
}
