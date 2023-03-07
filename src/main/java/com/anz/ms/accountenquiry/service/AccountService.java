package com.anz.ms.accountenquiry.service;

import com.anz.ms.accountenquiry.api.AccountResponseList;
import com.anz.ms.accountenquiry.api.TransactionResponseList;
import jakarta.validation.constraints.NotNull;

public interface AccountService {
    AccountResponseList retrieveAccounts();

    TransactionResponseList retrieveTransactions(@NotNull String accountNumber);
}
