package common;

import com.anz.ms.accountenquiry.repository.db.entity.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class TestDataProvider {

    public static Account getValidAccount(String accountName, String accountNumber, String accountTypeName, String currencyName, Double balance, LocalDate date) {
        AccountType savingsAccountType = AccountType.builder()
                .id(1)
                .name(accountTypeName)
                .build();

        return Account.builder()
                .accountId(1L)
                .accountNumber(accountNumber)
                .accountType(savingsAccountType)
                .currency(getCurrency(currencyName))
                .accountName(accountName)
                .balanceDate(date)
                .openingAvailableBalance(balance)
                .user(getUser("USERCODE", "USERNAME"))
                .build();
    }

    public static Transaction getValidTransaction(Account account, String description, String transactionCurrencyName, Double transactionAmount, LocalDate transactionDate) {
        return Transaction.builder()
                .transactionId(1L)
                .account(account)
                .transactionType(TransactionType.CREDIT)
                .transactionNarrative(description)
                .currency(TestDataProvider.getCurrency(transactionCurrencyName))
                .amount(transactionAmount)
                .valueDate(transactionDate)
                .build();
    }

    public static User getUser(String code, String name) {
        return User.builder()
                .id(1)
                .name(name)
                .userCode(code)
                .build();
    }

    public static Currency getCurrency(@NotNull String currencyName) {
        return Currency.builder()
                .id(1)
                .name(currencyName)
                .build();
    }
}
