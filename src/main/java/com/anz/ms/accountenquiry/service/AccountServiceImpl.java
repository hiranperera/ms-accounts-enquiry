package com.anz.ms.accountenquiry.service;

import com.anz.ms.accountenquiry.api.AccountResponse;
import com.anz.ms.accountenquiry.api.AccountResponseList;
import com.anz.ms.accountenquiry.api.TransactionResponse;
import com.anz.ms.accountenquiry.api.TransactionResponseList;
import com.anz.ms.accountenquiry.exception.AccountEntitlementFailureException;
import com.anz.ms.accountenquiry.exception.DataNotFoundException;
import com.anz.ms.accountenquiry.repository.db.AccountRepository;
import com.anz.ms.accountenquiry.repository.db.TransactionRepository;
import com.anz.ms.accountenquiry.repository.db.UserRepository;
import com.anz.ms.accountenquiry.repository.db.entity.Account;
import com.anz.ms.accountenquiry.repository.db.entity.Transaction;
import com.anz.ms.accountenquiry.repository.db.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final EntityResponseMapper entityResponseMapper;

    /**
     * Get the relevant accounts to a given user code.
     * @param userCode user code
     * @return List of accounts
     */
    @Override
    public AccountResponseList retrieveAccounts(@NotNull String userCode) {
        User user = userRepository.findByUserCode(userCode);

        if (user == null) {
            throw new DataNotFoundException(String.format("User not found for User Code: %s", userCode));
        }

        log.debug("message=\"User retrieved from the database for the user code: {}\"", userCode);

        List<Account> accounts = accountRepository.findByUser(user);

        log.debug("message=\"Accounts ({}) retrieved for the user: {}\"", accounts.size(), userCode);

        List<AccountResponse> accountResponses = accounts.stream().map(entityResponseMapper::mapAccountToAccountResponse)
                .collect(Collectors.toList());

        return AccountResponseList.builder().accountResponseList(accountResponses).userCode(userCode).build();
    }

    /**
     * Get the transactions for a given account id.
     * @param accountId account id
     * @return List of transactions.
     */
    @Override
    public TransactionResponseList retrieveTransactions(@NotNull Long accountId) {
        Account account = accountRepository.findByAccountId(accountId);

        if (account == null) {
            throw new DataNotFoundException(String.format("Account not found for Account Id: %d", accountId));
        }

        log.debug("message=\"Account retrieved from the database for the account id: {}\"", accountId);

        List<Transaction> transactions = transactionRepository.findByAccount(account);

        log.debug("message=\"Transactions ({}) retrieved from the database for the account id: {}\"", transactions.size(), accountId);

        List<TransactionResponse> transactionResponses = transactions.stream().map(entityResponseMapper::mapTransactionToTransactionResponse)
                .collect(Collectors.toList());

        return TransactionResponseList.builder()
                .account(account)
                .transactionResponseList(transactionResponses).build();
    }

    /**
     * Check the account entitlement for the user.
     * @param userCode User Code
     * @param accountId Account Id
     */
    @Override
    public void validateAccountEntitlement(String userCode, Long accountId) {
        Account account = accountRepository.findByAccountId(accountId);

        if (account == null) {
            throw new DataNotFoundException(String.format("Account not found for Account Id: %d", accountId));
        }

        log.debug("message=\"Account retrieved from the database for the account id: {}\"", accountId);

        if (account.getUser() != null && !userCode.equals(account.getUser().getUserCode())) {
            throw new AccountEntitlementFailureException(
                    String.format("Account (Account ID): %d & access is not entitled to the user (User Code): %s", accountId, userCode));
        }
    }
}
