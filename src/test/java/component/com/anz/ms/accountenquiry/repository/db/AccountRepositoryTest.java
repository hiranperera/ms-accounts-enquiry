package component.com.anz.ms.accountenquiry.repository.db;

import com.anz.ms.accountenquiry.Application;
import com.anz.ms.accountenquiry.repository.db.AccountRepository;
import com.anz.ms.accountenquiry.repository.db.UserRepository;
import com.anz.ms.accountenquiry.repository.db.entity.Account;
import com.anz.ms.accountenquiry.repository.db.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({SpringExtension.class})
@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByAccountNumberValidAccount() {
        Account account = accountRepository.findByAccountNumber("ACCNUMBER_123455");

        assertNotNull(account);
        assertEquals(account.getAccountNumber(), "ACCNUMBER_123455");
        assertEquals(account.getAccountType().getName(), "Savings");
        assertEquals(account.getAccountName(), "ACCNAME_OFFICE_1");
        assertEquals(account.getCurrency().getName(), "AUD");
        assertEquals(account.getOpeningAvailableBalance(), 3500.78);
        assertEquals(account.getBalanceDate().format(DateTimeFormatter.ISO_DATE), "2023-03-03");
        assertEquals(account.getUser().getUserCode(), "U0001");
        assertEquals(account.getUser().getName(), "Hiran Perera");
    }

    @Test
    public void findByAccountNumberInvalidAccount() {
        Account account = accountRepository.findByAccountNumber("INVALID_ACCOUNT_NUMBER");

        assertNull(account);
    }

    @Test
    public void findByUserValidUser() {
        User user = userRepository.findByUserCode("U0003");
        List<Account> accounts = accountRepository.findByUser(user);

        assertNotNull(accounts);
        assertEquals(accounts.size(), 2);

        assertEquals(accounts.get(0).getAccountNumber(), "ACCNUMBER_123456");
        assertEquals(accounts.get(0).getAccountType().getName(), "Savings");
        assertEquals(accounts.get(0).getAccountName(), "ACCNAME_PERSONAL_1");
        assertEquals(accounts.get(0).getCurrency().getName(), "AUD");
        assertEquals(accounts.get(0).getOpeningAvailableBalance(), 6800.57);
        assertEquals(accounts.get(0).getBalanceDate().format(DateTimeFormatter.ISO_DATE), "2023-03-02");
        assertEquals(accounts.get(0).getUser().getUserCode(), "U0003");
        assertEquals(accounts.get(0).getUser().getName(), "Mihiran Rupasinghe");

        assertEquals(accounts.get(1).getAccountNumber(), "ACCNUMBER_123457");
        assertEquals(accounts.get(1).getAccountType().getName(), "Savings");
        assertEquals(accounts.get(1).getAccountName(), "ACCNAME_PERSONAL_2");
        assertEquals(accounts.get(1).getCurrency().getName(), "AUD");
        assertEquals(accounts.get(1).getOpeningAvailableBalance(), 9000.33);
        assertEquals(accounts.get(1).getBalanceDate().format(DateTimeFormatter.ISO_DATE), "2023-03-01");
        assertEquals(accounts.get(1).getUser().getUserCode(), "U0003");
        assertEquals(accounts.get(1).getUser().getName(), "Mihiran Rupasinghe");
    }

    @Test
    public void findByUserInvalidUser() {
        User user = userRepository.findByUserCode("INVALID_USER_CODE");

        assertNull(user);
    }
}
