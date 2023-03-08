package component.com.anz.ms.accountenquiry.repository.db;

import com.anz.ms.accountenquiry.Application;
import com.anz.ms.accountenquiry.repository.db.AccountRepository;
import com.anz.ms.accountenquiry.repository.db.entity.Account;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({SpringExtension.class})
@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void findByAccountNumberValidAccount() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

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
}
