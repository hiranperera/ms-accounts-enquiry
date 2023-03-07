package com.anz.ms.accountenquiry.api;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class TransactionResponse {
    private String accountNumber;
    private String accountName;
    private Timestamp valueDate;
    private int currency;
    private double debitAmount;
    private double creditAmount;
    private String transactionType;
    private String transactionNarrative;
}
