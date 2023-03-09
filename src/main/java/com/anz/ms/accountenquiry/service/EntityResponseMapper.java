package com.anz.ms.accountenquiry.service;

import com.anz.ms.accountenquiry.api.AccountResponse;
import com.anz.ms.accountenquiry.api.TransactionResponse;
import com.anz.ms.accountenquiry.repository.db.entity.Account;
import com.anz.ms.accountenquiry.repository.db.entity.Transaction;
import com.anz.ms.accountenquiry.repository.db.entity.TransactionType;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class EntityResponseMapper {

    public AccountResponse mapAccountToAccountResponse(@NotNull Account account) {
        return AccountResponse.builder()
                .accountId(account.getAccountId())
                .accountNumber(account.getAccountNumber())
                .accountName(account.getAccountName())
                .accountType(account.getAccountType().getName())
                .balanceDate(account.getBalanceDate())
                .currency(account.getCurrency().getName())
                .openingAvailableBalance(account.getOpeningAvailableBalance())
                .build();
    }

    public TransactionResponse mapTransactionToTransactionResponse(@NotNull Transaction transaction) {
        return TransactionResponse.builder()
                .accountNumber(transaction.getAccount().getAccountNumber())
                .accountName(transaction.getAccount().getAccountName())
                .valueDate(transaction.getValueDate())
                .currency(transaction.getCurrency().getName())
                .debitAmount(transaction.getTransactionType().equals(TransactionType.DEBIT) ? transaction.getAmount() : 0)
                .creditAmount(transaction.getTransactionType().equals(TransactionType.CREDIT) ? transaction.getAmount() : 0)
                .transactionType(transaction.getTransactionType().getName())
                .transactionNarrative(transaction.getTransactionNarrative())
                .build();
    }
}
