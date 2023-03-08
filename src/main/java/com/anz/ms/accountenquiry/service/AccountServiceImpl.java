package com.anz.ms.accountenquiry.service;

import com.anz.ms.accountenquiry.api.AccountResponse;
import com.anz.ms.accountenquiry.api.AccountResponseList;
import com.anz.ms.accountenquiry.api.TransactionResponse;
import com.anz.ms.accountenquiry.api.TransactionResponseList;
import com.anz.ms.accountenquiry.exception.DataNotFoundException;
import com.anz.ms.accountenquiry.repository.db.AccountRepository;
import com.anz.ms.accountenquiry.repository.db.TransactionRepository;
import com.anz.ms.accountenquiry.repository.db.UserRepository;
import com.anz.ms.accountenquiry.repository.db.entity.Account;
import com.anz.ms.accountenquiry.repository.db.entity.Transaction;
import com.anz.ms.accountenquiry.repository.db.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final EntityResponseMapper entityResponseMapper;

    @Override
    public AccountResponseList retrieveAccounts(@NotNull String userCode) {
        User user = userRepository.findByUserCode(userCode);

        if (user == null)
            throw new DataNotFoundException(String.format("User not found for User Code: %s", userCode));

        List<Account> accounts = accountRepository.findByUser(user);

        List<AccountResponse> accountResponses = accounts.stream().map(entityResponseMapper::mapAccountToAccountResponse)
                .collect(Collectors.toList());

        return AccountResponseList.builder().accountResponseList(accountResponses).httpStatus(HttpStatus.OK).build();
    }

    @Override
    public TransactionResponseList retrieveTransactions(@NotNull String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);

        if (account == null)
            throw new DataNotFoundException(String.format("Account not found for Account Number: %s", accountNumber));

        List<Transaction> transactions = transactionRepository.findByAccount(account);

        List<TransactionResponse> transactionResponses = transactions.stream().map(entityResponseMapper::mapTransactionToTransactionResponse)
                .collect(Collectors.toList());

        return TransactionResponseList.builder()
                .account(account)
                .transactionResponseList(transactionResponses).httpStatus(HttpStatus.OK).build();
    }
}
