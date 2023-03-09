package com.anz.ms.accountenquiry.controller;

import com.anz.ms.accountenquiry.api.AccountResponse;
import com.anz.ms.accountenquiry.api.AccountResponseList;
import com.anz.ms.accountenquiry.api.TransactionResponseList;
import com.anz.ms.accountenquiry.service.AccountService;
import com.anz.ms.accountenquiry.validator.UserParamValidator;
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
public class AccountEnquiryController {

    private final AccountService accountService;
    private final UserParamValidator userParamValidator;

    @GetMapping("/account-enquiry/users/{user-code}/accounts")
    public ResponseEntity<AccountResponseList> retrieveAccounts(
            @PathVariable(name = "user-code") final String userCode
    ) {
      log.info("message=\"Accounts retrieval request received\"");

      userParamValidator.validateUserCode(userCode);

      AccountResponseList accountResponseList = accountService.retrieveAccounts(userCode);

      for (AccountResponse accountResponse : accountResponseList.getAccountResponseList()) {
          accountResponse.add(linkTo(methodOn(AccountEnquiryController.class)
                  .retrieveTransactions(accountResponse.getAccountNumber())).withRel("transactions"));
      }

      return new ResponseEntity<>(accountResponseList, accountResponseList.getHttpStatus());
    }

    @GetMapping("/account-enquiry/accounts/{account-number}/transactions")
    public ResponseEntity<TransactionResponseList> retrieveTransactions(
            @PathVariable(name = "account-number") final String accountNumber
    ) {
        log.info("message=\"Transaction retrieval request received\"");

        userParamValidator.validateAccountNumber(accountNumber);

        TransactionResponseList transactionResponseList = accountService.retrieveTransactions(accountNumber);
        transactionResponseList.add(linkTo(methodOn(AccountEnquiryController.class).retrieveAccounts(
                transactionResponseList.getAccount().getUser().getUserCode())).withRel("accounts"));

        return new ResponseEntity<>(transactionResponseList, transactionResponseList.getHttpStatus());
    }
}
