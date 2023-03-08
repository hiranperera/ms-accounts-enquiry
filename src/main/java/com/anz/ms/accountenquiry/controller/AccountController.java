package com.anz.ms.accountenquiry.controller;

import com.anz.ms.accountenquiry.api.AccountResponse;
import com.anz.ms.accountenquiry.api.AccountResponseList;
import com.anz.ms.accountenquiry.api.TransactionResponseList;
import com.anz.ms.accountenquiry.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/account-enquiry/user/{user-code}/accounts")
    public ResponseEntity<AccountResponseList> retrieveAllAccount(
            @PathVariable(name = "user-code") String userCode
    ) {
      log.debug("message=\"All account retrieval request received\"");

      AccountResponseList accountResponseList = accountService.retrieveAccounts(userCode);

      for (AccountResponse accountResponse : accountResponseList.getAccountResponseList()) {
          accountResponse.add(linkTo(methodOn(AccountController.class)
                  .retrieveTransactions(accountResponse.getAccountNumber())).withRel("transactions"));
      }

      return new ResponseEntity<>(accountResponseList, accountResponseList.getHttpStatus());
    }

    @GetMapping("/account-enquiry/account/{account-number}/transactions")
    public ResponseEntity<TransactionResponseList> retrieveTransactions(
            @PathVariable(name = "account-number") String accountNumber
    ) {
        log.debug("message=\"Transaction retrieval request received\"");

        TransactionResponseList transactionResponseList = accountService.retrieveTransactions(accountNumber);
        transactionResponseList.add(linkTo(methodOn(AccountController.class).retrieveAllAccount(
                transactionResponseList.getAccount().getUser().getUserCode())).withRel("accounts"));

        return new ResponseEntity<>(transactionResponseList, transactionResponseList.getHttpStatus());
    }
}
