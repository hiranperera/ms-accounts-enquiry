package com.anz.ms.accountenquiry.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@Data
@Builder
public class AccountResponse extends RepresentationModel<AccountResponse> {

    @JsonIgnore
    private Long accountId;
    private String accountNumber;
    private String accountName;
    private String accountType;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate balanceDate;

    private String currency;
    private Double openingAvailableBalance;
}
