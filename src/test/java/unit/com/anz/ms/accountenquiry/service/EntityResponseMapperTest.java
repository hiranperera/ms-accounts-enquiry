package unit.com.anz.ms.accountenquiry.service;

import com.anz.ms.accountenquiry.api.AccountResponse;
import com.anz.ms.accountenquiry.api.TransactionResponse;
import com.anz.ms.accountenquiry.repository.db.entity.Account;
import com.anz.ms.accountenquiry.repository.db.entity.Transaction;
import com.anz.ms.accountenquiry.repository.db.entity.TransactionType;
import com.anz.ms.accountenquiry.service.EntityResponseMapper;
import common.TestDataProvider;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EntityResponseMapperTest {

    @Test
    public void mapAccountResponseTest() {
        final String accountNumber = "ACCNUMBER001";
        final String accountName = "ACCNAME";
        final String accountTypeName = "Savings";
        final String currencyName = "AUD";
        final Double balance = 2304.30;
        final LocalDate date = LocalDate.now();

        EntityResponseMapper entityResponseMapper = new EntityResponseMapper();

        AccountResponse accountResponse = entityResponseMapper.mapAccountToAccountResponse(TestDataProvider.getValidAccount(
                accountName, accountNumber, accountTypeName, currencyName, balance, date));

        assertEquals(accountResponse.getAccountName(), accountName);
        assertEquals(accountResponse.getAccountNumber(), accountNumber);
        assertEquals(accountResponse.getAccountType(), accountTypeName);
        assertEquals(accountResponse.getCurrency(), currencyName);
        assertEquals(accountResponse.getOpeningAvailableBalance(), balance);
        assertEquals(accountResponse.getBalanceDate(), date);
    }

    @Test
    public void mapCreditTransactionResponseTest() {
        final String accountNumber = "ACCNUMBER001";
        final String accountName = "ACCNAME";
        final String accountTypeName = "Savings";
        final String accountCurrencyName = "AUD";
        final String transactionCurrencyName = "SGD";
        final Double accountBalance = 2304.30;
        final Double transactionAmount = 100.50;
        final LocalDate accountDate = LocalDate.now();
        final LocalDate transactionDate = LocalDate.now();
        final String description = "Test description";

        Account account = TestDataProvider.getValidAccount(
                accountName, accountNumber, accountTypeName, accountCurrencyName, accountBalance, accountDate);

        Transaction transaction = Transaction.builder()
                .transactionId(1L)
                .account(account)
                .transactionType(TransactionType.CREDIT)
                .transactionNarrative(description)
                .currency(TestDataProvider.getCurrency(transactionCurrencyName))
                .amount(transactionAmount)
                .valueDate(transactionDate)
                .build();

        EntityResponseMapper entityResponseMapper = new EntityResponseMapper();

        TransactionResponse transactionResponse = entityResponseMapper.mapTransactionToTransactionResponse(transaction);

        assertEquals(transactionResponse.getTransactionType(), TransactionType.CREDIT.getName());
        assertEquals(transactionResponse.getAccountName(), accountName);
        assertEquals(transactionResponse.getCurrency(), transactionCurrencyName);
        assertEquals(transactionResponse.getTransactionNarrative(), description);
        assertEquals(transactionResponse.getAccountNumber(), accountNumber);
        assertEquals(transactionResponse.getCreditAmount(), transactionAmount);
        assertEquals(transactionResponse.getDebitAmount(), 0);
        assertEquals(transactionResponse.getValueDate(), transactionDate);
    }

    @Test
    public void mapDebitTransactionResponseTest() {
        final String accountNumber = "ACCNUMBER001";
        final String accountName = "ACCNAME";
        final String accountTypeName = "Savings";
        final String accountCurrencyName = "AUD";
        final String transactionCurrencyName = "SGD";
        final Double accountBalance = 2304.30;
        final Double transactionAmount = 100.50;
        final LocalDate accountDate = LocalDate.now();
        final LocalDate transactionDate = LocalDate.now();
        final String description = "Test description";

        Account account = TestDataProvider.getValidAccount(
                accountName, accountNumber, accountTypeName, accountCurrencyName, accountBalance, accountDate);

        Transaction transaction = Transaction.builder()
                .transactionId(1L)
                .account(account)
                .transactionType(TransactionType.DEBIT)
                .transactionNarrative(description)
                .currency(TestDataProvider.getCurrency(transactionCurrencyName))
                .amount(transactionAmount)
                .valueDate(transactionDate)
                .build();

        EntityResponseMapper entityResponseMapper = new EntityResponseMapper();

        TransactionResponse transactionResponse = entityResponseMapper.mapTransactionToTransactionResponse(transaction);

        assertEquals(transactionResponse.getTransactionType(), TransactionType.DEBIT.getName());
        assertEquals(transactionResponse.getAccountName(), accountName);
        assertEquals(transactionResponse.getCurrency(), transactionCurrencyName);
        assertEquals(transactionResponse.getTransactionNarrative(), description);
        assertEquals(transactionResponse.getAccountNumber(), accountNumber);
        assertEquals(transactionResponse.getCreditAmount(), 0);
        assertEquals(transactionResponse.getDebitAmount(), transactionAmount);
        assertEquals(transactionResponse.getValueDate(), transactionDate);
    }
}
