package com.anz.ms.accountenquiry.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AccountResponseList {
    @JsonIgnore
    private String userCode;
    private List<AccountResponse> accountResponseList;
}
