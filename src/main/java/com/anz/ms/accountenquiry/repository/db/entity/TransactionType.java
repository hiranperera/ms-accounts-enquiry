package com.anz.ms.accountenquiry.repository.db.entity;

import lombok.Getter;

@Getter
public enum TransactionType {

    CREDIT (1, "Credit"),
    DEBIT (2, "Debit");

    private final Integer id;
    private final String name;

    TransactionType(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
