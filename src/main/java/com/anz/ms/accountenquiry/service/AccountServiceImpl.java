package com.anz.ms.accountenquiry.service;

import com.anz.ms.accountenquiry.api.AccountResponse;
import com.anz.ms.accountenquiry.api.AccountResponseList;
import com.anz.ms.accountenquiry.api.TransactionResponse;
import com.anz.ms.accountenquiry.api.TransactionResponseList;
import com.anz.ms.accountenquiry.repository.db.AccountRepository;
import com.anz.ms.accountenquiry.repository.db.TransactionRepository;
import com.anz.ms.accountenquiry.repository.db.entity.Account;
import com.anz.ms.accountenquiry.repository.db.entity.Transaction;
import com.anz.ms.accountenquiry.repository.db.entity.TransactionType;
import jakarta.validation.constraints.NotNull;
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
                    .accountType(a.getAccountType().getName())
                    .balanceDate(a.getBalanceDate())
                    .currency(a.getCurrency().getName())
                    .openingAvailableBalance(a.getOpeningAvailableBalance())
                    .build()
        ).toList();

        return AccountResponseList.builder().accountResponseList(accountResponses).httpStatus(HttpStatus.OK).build();
    }

    @Override
    public TransactionResponseList retrieveTransactions(@NotNull String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        List<Transaction> transactions = transactionRepository.findByAccount(account);

        List<TransactionResponse> transactionResponses = transactions.stream().map(t ->
                TransactionResponse.builder()
                        .accountNumber(t.getAccount().getAccountNumber())
                        .accountName(t.getAccount().getAccountName())
                        .valueDate(t.getValueDate())
                        .currency(t.getCurrency().getName())
                        .debitAmount(t.getTransactionType().equals(TransactionType.CREDIT) ? t.getAmount() : 0)
                        .creditAmount(t.getTransactionType().equals(TransactionType.DEBIT) ? t.getAmount() : 0)
                        .transactionType(t.getTransactionType().getName())
                        .transactionNarrative(t.getTransactionNarrative())
                        .build()).toList();

        return TransactionResponseList.builder().transactionResponseList(transactionResponses).httpStatus(HttpStatus.OK).build();
    }
}
