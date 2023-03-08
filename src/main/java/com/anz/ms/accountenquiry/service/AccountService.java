package com.anz.ms.accountenquiry.service;

import com.anz.ms.accountenquiry.api.AccountResponseList;
import com.anz.ms.accountenquiry.api.TransactionResponseList;

import javax.validation.constraints.NotNull;

public interface AccountService {
    AccountResponseList retrieveAccounts(@NotNull String userCode);

    TransactionResponseList retrieveTransactions(@NotNull String accountNumber);
}
