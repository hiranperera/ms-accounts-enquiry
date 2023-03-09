package com.anz.ms.accountenquiry.api;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AccountResponseList {
    private List<AccountResponse> accountResponseList;
}
