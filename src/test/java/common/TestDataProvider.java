package common;

import com.anz.ms.accountenquiry.repository.db.entity.Account;
import com.anz.ms.accountenquiry.repository.db.entity.AccountType;
import com.anz.ms.accountenquiry.repository.db.entity.Currency;
import com.anz.ms.accountenquiry.repository.db.entity.User;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class TestDataProvider {

    public static Account getValidAccount(String accountName, String accountNumber, String accountTypeName, String currencyName, Double balance, LocalDate date) {
        AccountType savingsAccountType = AccountType.builder()
                .id(1)
                .name(accountTypeName)
                .build();

        User user = User.builder()
                .id(1)
                .name("USERNAME")
                .userCode("USERCODE001")
                .build();

        Account account = Account.builder()
                .accountId(1L)
                .accountNumber(accountNumber)
                .accountType(savingsAccountType)
                .currency(getCurrency(currencyName))
                .accountName(accountName)
                .balanceDate(date)
                .openingAvailableBalance(balance)
                .user(user)
                .build();

        return account;
    }

    public static Currency getCurrency(@NotNull String currencyName) {
        return Currency.builder()
                .id(1)
                .name(currencyName)
                .build();
    }
}
