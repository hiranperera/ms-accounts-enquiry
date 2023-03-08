package com.anz.ms.accountenquiry.api;

import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.sql.Timestamp;

@Data
@Builder
public class AccountResponse extends RepresentationModel<AccountResponse> {

    private String accountNumber;
    private String accountName;
    private String accountType;
    private Timestamp balanceDate;
    private String currency;
    private Double openingAvailableBalance;
}
