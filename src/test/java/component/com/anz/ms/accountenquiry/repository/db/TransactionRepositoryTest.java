package component.com.anz.ms.accountenquiry.repository.db;

import com.anz.ms.accountenquiry.Application;
import com.anz.ms.accountenquiry.repository.db.AccountRepository;
import com.anz.ms.accountenquiry.repository.db.TransactionRepository;
import com.anz.ms.accountenquiry.repository.db.entity.Account;
import com.anz.ms.accountenquiry.repository.db.entity.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith({SpringExtension.class})
@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
public class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void findByAccountValidAccountTest() {

        Account account = accountRepository.findByAccountNumber("ACCNUMBER_123457");

        List<Transaction> transactions = transactionRepository.findByAccount(account);

        assertNotNull(transactions);
        assertEquals(transactions.size(), 2);
        assertEquals(transactions.get(0).getTransactionType().getName(), "Credit");
        assertEquals(transactions.get(0).getTransactionNarrative(), "test transaction");
        assertEquals(transactions.get(0).getCurrency().getName(), "AUD");
        assertEquals(transactions.get(0).getAmount(), 100.35);
        assertEquals(transactions.get(0).getValueDate().format(DateTimeFormatter.ISO_DATE), "2023-03-01");
        assertEquals(transactions.get(0).getAccount().getAccountNumber(), "ACCNUMBER_123457");

        assertEquals(transactions.get(1).getTransactionType().getName(), "Debit");
        assertEquals(transactions.get(1).getTransactionNarrative(), "test transaction");
        assertEquals(transactions.get(1).getCurrency().getName(), "AUD");
        assertEquals(transactions.get(1).getAmount(), 700.5);
        assertEquals(transactions.get(1).getValueDate().format(DateTimeFormatter.ISO_DATE), "2023-03-02");
        assertEquals(transactions.get(1).getAccount().getAccountNumber(), "ACCNUMBER_123457");
    }

    @Test
    public void findByAccountWithNoTransactionTest() {

        Account account = accountRepository.findByAccountNumber("ACCNAME_PERSONAL_1");

        List<Transaction> transactions = transactionRepository.findByAccount(account);

        assertNotNull(transactions);
        assertEquals(transactions.size(), 0);
    }
}
