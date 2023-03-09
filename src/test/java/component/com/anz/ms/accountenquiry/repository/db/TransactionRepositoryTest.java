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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith({SpringExtension.class})
@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"/db/cleanup.sql", "/db/test_data.sql"})
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = {"/db/cleanup.sql"})
public class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void findByAccountAvailableAccountTest() {

        Account account = accountRepository.findByAccountId(3L);

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
    public void findByAccountWithNoTransactionAvailableTest() {

        Account account = accountRepository.findByAccountId(2L);

        List<Transaction> transactions = transactionRepository.findByAccount(account);

        assertNotNull(transactions);
        assertEquals(transactions.size(), 0);
    }
}
