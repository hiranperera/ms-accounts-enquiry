package com.anz.ms.accountenquiry.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class TransactionResponseList {
    private List<TransactionResponse> transactionResponseList;

    @JsonIgnore
    private HttpStatus httpStatus;
}
