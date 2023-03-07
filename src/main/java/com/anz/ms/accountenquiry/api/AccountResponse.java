package com.anz.ms.accountenquiry.api;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
public class AccountResponse {

    private String accountNumber;
    private String accountName;
    private int accountType;
    private Timestamp balanceDate;
    private int currency;
    private Double openingAvailableBalance;
}
