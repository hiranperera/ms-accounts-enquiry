package unit.com.anz.ms.accountenquiry.service;

import com.anz.ms.accountenquiry.api.AccountResponse;
import com.anz.ms.accountenquiry.api.AccountResponseList;
import com.anz.ms.accountenquiry.api.TransactionResponse;
import com.anz.ms.accountenquiry.api.TransactionResponseList;
import com.anz.ms.accountenquiry.exception.DataNotFoundException;
import com.anz.ms.accountenquiry.repository.db.AccountRepository;
import com.anz.ms.accountenquiry.repository.db.TransactionRepository;
import com.anz.ms.accountenquiry.repository.db.UserRepository;
import com.anz.ms.accountenquiry.repository.db.entity.Account;
import com.anz.ms.accountenquiry.service.AccountServiceImpl;
import com.anz.ms.accountenquiry.service.EntityResponseMapper;
import common.TestDataProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private EntityResponseMapper entityResponseMapper;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    public void testRetrieveTransactionsForValidAccount() {
        String accountNumber = "ACCNUMBER1";
        String accountName = "ACCNAME1";
        String accountType = "Current";
        String accountCurrency = "SGD";
        Double accountBalance = 100.25;
        LocalDate date = LocalDate.now();

        String transactionDescription1 = "Description 1";
        String transactionCurrency1 = "SGD";
        Double transactionAmount1 = 250.20;
        String transactionDescription2 = "Description 2";
        String transactionCurrency2 = "AUD";
        Double transactionAmount2 = 568.20;
        String transactionType1 = "Credit";
        String transactionType2 = "Debit";

        Account account = TestDataProvider.getValidAccount(accountNumber, accountName, accountType, accountCurrency, accountBalance, date);

        when(accountRepository.findByAccountNumber(any())).thenReturn(account);
        when(transactionRepository.findByAccount(any())).thenReturn(List.of(
                TestDataProvider.getValidTransaction(account, transactionDescription1, transactionCurrency1, transactionAmount1, date),
                TestDataProvider.getValidTransaction(account, transactionDescription2, transactionCurrency2, transactionAmount2, date)
        ));

        when(entityResponseMapper.mapTransactionToTransactionResponse(any()))
                .thenReturn(TransactionResponse.builder()
                        .currency(transactionCurrency1)
                        .transactionNarrative(transactionDescription1)
                        .transactionType(transactionType1)
                        .creditAmount(transactionAmount1)
                        .accountNumber(accountNumber)
                        .accountName(accountName)
                        .valueDate(date)
                        .build())
                .thenReturn(TransactionResponse.builder()
                        .currency(transactionCurrency2)
                        .transactionNarrative(transactionDescription2)
                        .transactionType(transactionType2)
                        .debitAmount(transactionAmount2)
                        .accountNumber(accountNumber)
                        .accountName(accountName)
                        .valueDate(date)
                        .build());

        TransactionResponseList transactionResponseList = accountService.retrieveTransactions(any());

        assertNotNull(transactionResponseList);
        assertNotNull(transactionResponseList.getTransactionResponseList());
        assertEquals(transactionResponseList.getTransactionResponseList().size(), 2);

        assertEquals(transactionResponseList.getTransactionResponseList().get(0).getAccountName(), accountName);
        assertEquals(transactionResponseList.getTransactionResponseList().get(0).getAccountNumber(), accountNumber);
        assertEquals(transactionResponseList.getTransactionResponseList().get(0).getTransactionType(), transactionType1);
        assertEquals(transactionResponseList.getTransactionResponseList().get(0).getTransactionNarrative(), transactionDescription1);
        assertEquals(transactionResponseList.getTransactionResponseList().get(0).getValueDate(), date);
        assertEquals(transactionResponseList.getTransactionResponseList().get(0).getCurrency(), transactionCurrency1);
        assertEquals(transactionResponseList.getTransactionResponseList().get(0).getCreditAmount(), transactionAmount1);
        assertEquals(transactionResponseList.getTransactionResponseList().get(0).getDebitAmount(), 0);

        assertEquals(transactionResponseList.getTransactionResponseList().get(1).getAccountName(), accountName);
        assertEquals(transactionResponseList.getTransactionResponseList().get(1).getAccountNumber(), accountNumber);
        assertEquals(transactionResponseList.getTransactionResponseList().get(1).getTransactionType(), transactionType2);
        assertEquals(transactionResponseList.getTransactionResponseList().get(1).getTransactionNarrative(), transactionDescription2);
        assertEquals(transactionResponseList.getTransactionResponseList().get(1).getValueDate(), date);
        assertEquals(transactionResponseList.getTransactionResponseList().get(1).getCurrency(), transactionCurrency2);
        assertEquals(transactionResponseList.getTransactionResponseList().get(1).getDebitAmount(), transactionAmount2);
        assertEquals(transactionResponseList.getTransactionResponseList().get(1).getCreditAmount(), 0);
    }

    @Test
    public void testRetrieveTransactionsForInvalidAccount() {
        when(accountRepository.findByAccountNumber(any())).thenReturn(null);

        DataNotFoundException exception = assertThrows(DataNotFoundException.class, () -> accountService.retrieveTransactions("INVALID_ACC"));

        assertEquals(exception.getMessage(), "Account not found for Account Number: INVALID_ACC");
    }

    @Test
    public void testRetrieveMultipleValidAccountsForValidUser() {
        String accountNumber1 = "ACCNUMBER1";
        String accountNumber2 = "ACCNUMBER2";
        String accountName1 = "ACCNAME1";
        String accountName2 = "ACCNAME2";
        String accountType1 = "Current";
        String accountType2 = "Savings";
        String accountCurrency1 = "SGD";
        String accountCurrency2 = "AUD";
        Double accountBalance1 = 100.25;
        Double accountBalance2 = 500.58;
        LocalDate date = LocalDate.now();

        when(userRepository.findByUserCode(any())).thenReturn(TestDataProvider.getUser("TESTUSERCODE", "Test User Name"));
        when(accountRepository.findByUser(any())).thenReturn(List.of(
                TestDataProvider.getValidAccount(accountName1, accountNumber1, accountType1, accountCurrency1, accountBalance1, date),
                TestDataProvider.getValidAccount(accountName2, accountNumber2, accountType2, accountCurrency2, accountBalance2, date))
        );
        when(entityResponseMapper.mapAccountToAccountResponse(any()))
                .thenReturn(AccountResponse.builder()
                        .accountNumber(accountNumber1)
                        .accountName(accountName1)
                        .accountType(accountType1)
                        .openingAvailableBalance(accountBalance1)
                        .balanceDate(date)
                        .currency(accountCurrency1).build())
                .thenReturn(AccountResponse.builder()
                        .accountNumber(accountNumber2)
                        .accountName(accountName2)
                        .accountType(accountType2)
                        .openingAvailableBalance(accountBalance2)
                        .balanceDate(date)
                        .currency(accountCurrency2).build());

        AccountResponseList accountResponseList = accountService.retrieveAccounts(any());

        assertNotNull(accountResponseList);
        assertNotNull(accountResponseList.getAccountResponseList());
        assertEquals(accountResponseList.getAccountResponseList().size(), 2);

        assertEquals(accountResponseList.getAccountResponseList().get(0).getAccountName(), accountName1);
        assertEquals(accountResponseList.getAccountResponseList().get(0).getAccountNumber(), accountNumber1);
        assertEquals(accountResponseList.getAccountResponseList().get(0).getAccountType(), accountType1);
        assertEquals(accountResponseList.getAccountResponseList().get(0).getCurrency(), accountCurrency1);
        assertEquals(accountResponseList.getAccountResponseList().get(0).getBalanceDate(), date);
        assertEquals(accountResponseList.getAccountResponseList().get(0).getOpeningAvailableBalance(), accountBalance1);

        assertEquals(accountResponseList.getAccountResponseList().get(1).getAccountName(), accountName2);
        assertEquals(accountResponseList.getAccountResponseList().get(1).getAccountNumber(), accountNumber2);
        assertEquals(accountResponseList.getAccountResponseList().get(1).getAccountType(), accountType2);
        assertEquals(accountResponseList.getAccountResponseList().get(1).getCurrency(), accountCurrency2);
        assertEquals(accountResponseList.getAccountResponseList().get(1).getBalanceDate(), date);
        assertEquals(accountResponseList.getAccountResponseList().get(1).getOpeningAvailableBalance(), accountBalance2);
    }

    @Test
    public void testRetrieveAccountsForInvalidUser() {
        when(accountRepository.findByAccountNumber(any())).thenReturn(null);

        var exception = assertThrows(DataNotFoundException.class, () -> accountService.retrieveAccounts("INVALID_USER"));

        assertEquals(exception.getMessage(), "User not found for User Code: INVALID_USER");
    }
}
