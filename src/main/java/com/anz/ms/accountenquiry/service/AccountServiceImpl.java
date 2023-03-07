package com.anz.ms.accountenquiry.service;

import com.anz.ms.accountenquiry.api.AccountResponse;
import com.anz.ms.accountenquiry.api.AccountResponseList;
import com.anz.ms.accountenquiry.api.TransactionResponse;
import com.anz.ms.accountenquiry.api.TransactionResponseList;
import com.anz.ms.accountenquiry.repository.db.AccountRepository;
import com.anz.ms.accountenquiry.repository.db.TransactionRepository;
import com.anz.ms.accountenquiry.repository.db.entity.Account;
import com.anz.ms.accountenquiry.repository.db.entity.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public AccountResponseList retrieveAccounts() {
        List<Account> accounts = accountRepository.findAll();

        List<AccountResponse> accountResponses = accounts.stream().map(a ->
            AccountResponse.builder()
                    .accountNumber(a.getAccountNumber())
                    .accountName(a.getAccountName())
                    .accountType(a.getAccountType())
                    .balanceDate(a.getBalanceDate())
                    .currency(a.getCurrency())
                    .openingAvailableBalance(a.getOpeningAvailableBalance())
                    .build()
        ).toList();

        return AccountResponseList.builder().accountResponseList(accountResponses).httpStatus(HttpStatus.OK).build();
    }

    @Override
    public TransactionResponseList retrieveTransactions(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        List<Transaction> transactions = transactionRepository.findByAccount(account);

        List<TransactionResponse> transactionResponses = transactions.stream().map(t ->
                TransactionResponse.builder()
                        .accountNumber(t.getAccount().getAccountNumber())
                        .accountName(t.getAccount().getAccountName())
                        .valueDate(t.getValueDate())
                        .currency(t.getCurrency())
                        .debitAmount(t.getTransactionType() == 1 ? t.getAmount() : 0)
                        .creditAmount(t.getTransactionType() == 2 ? t.getAmount() : 0)
                        .transactionType(t.getTransactionType() == 1 ? "Credit": "Debit")
                        .transactionNarrative(t.getTransactionNarrative())
                        .build()).toList();

        return TransactionResponseList.builder().transactionResponseList(transactionResponses).httpStatus(HttpStatus.OK).build();
    }
}
