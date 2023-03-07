package com.anz.ms.accountenquiry.service;

import com.anz.ms.accountenquiry.api.AccountResponseList;
import com.anz.ms.accountenquiry.api.TransactionResponseList;

public interface AccountService {
    AccountResponseList retrieveAccounts();

    TransactionResponseList retrieveTransactions(String accountNumber);
}
