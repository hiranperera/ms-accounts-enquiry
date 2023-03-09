package com.anz.ms.accountenquiry.api;

import com.anz.ms.accountenquiry.repository.db.entity.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
@Builder
public class TransactionResponseList extends RepresentationModel<TransactionResponseList> {
    private List<TransactionResponse> transactionResponseList;

    @JsonIgnore
    @NonNull
    private Account account;
}
