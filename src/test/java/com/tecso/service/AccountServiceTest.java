package com.tecso.service;
import com.tecso.dto.AccountDTO;
import com.tecso.entity.Account;
import com.tecso.enums.CurrencyType;
import com.tecso.repository.AccountRepository;
import com.tecso.service.impl.AccountServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.mockito.Mock;
import static org.junit.Assert.assertEquals;
import org.mockito.Spy;
import org.mockito.InjectMocks;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AccountServiceTest {

    @Spy
    @InjectMocks
    AccountServiceImpl accountService;

    @Mock
    AccountRepository accountRepository;

    private Account account;

    private String accountNumber;

    @Before
    public void setUp() {
        account = new Account();
        account.setAccountNumber("11");
        account.setBalance(0.00);
        account.setCurrency(CurrencyType.ARS);

        accountNumber = "11";
    }

    @Test
    public void createAccountOk() throws Exception {

        AccountDTO accountDTO = accountService.createAccount(account);

        assertEquals(accountDTO.getAccountNumber(), account.getAccountNumber());
    }

    @Test
    public void deleteAccountOk() throws Exception {

        AccountDTO accountDTO = accountService.deleteAccount(accountNumber);

        assertEquals(accountDTO.getAccountNumber(), account.getAccountNumber());
    }


}
