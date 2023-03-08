package unit.com.anz.ms.accountenquiry.service;

import com.anz.ms.accountenquiry.api.AccountResponse;
import com.anz.ms.accountenquiry.api.AccountResponseList;
import com.anz.ms.accountenquiry.repository.db.AccountRepository;
import com.anz.ms.accountenquiry.repository.db.TransactionRepository;
import com.anz.ms.accountenquiry.repository.db.UserRepository;;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
                TestDataProvider.getValidAccount(accountName1, accountNumber1, accountType1, accountCurrency1, accountBalance1,date),
                TestDataProvider.getValidAccount(accountName2, accountNumber2, accountType2, accountCurrency2, accountBalance2, date))
        );
        when(entityResponseMapper.mapAccountResponse(any()))
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
}
