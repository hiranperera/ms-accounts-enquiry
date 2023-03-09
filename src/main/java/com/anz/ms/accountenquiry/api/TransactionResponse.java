package com.anz.ms.accountenquiry.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class TransactionResponse {
    private String accountNumber;
    private String accountName;

    @JsonFormat(pattern = "MMM, dd, yyyy")
    private LocalDate valueDate;

    private String currency;
    private double debitAmount;
    private double creditAmount;
    private String transactionType;
    private String transactionNarrative;
}
