package com.tecso.service;


import com.tecso.dto.AccountDTO;
import com.tecso.dto.TransactionDTO;
import com.tecso.dto.TransactionRequestDTO;
import com.tecso.entity.Account;
import com.tecso.enums.CurrencyType;
import com.tecso.enums.TransactionType;
import com.tecso.repository.AccountRepository;
import com.tecso.service.impl.AccountServiceImpl;
import com.tecso.service.impl.TransactionServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TransactionServiceTest {

    @Spy
    @InjectMocks
    TransactionServiceImpl transactionService;

    @Mock
    AccountServiceImpl accountService;

    @Mock
    AccountRepository accountRepository;

    private TransactionRequestDTO transactionRequestDTO;

    private String accountNumber;

    @Before
    public void setUp() {

        //Set Up Transaction
        transactionRequestDTO = new TransactionRequestDTO();
        transactionRequestDTO.setAccountNumber("10");
        transactionRequestDTO.setAmount(100.00);
        transactionRequestDTO.setCurrency(CurrencyType.EURO);
        transactionRequestDTO.setTransactionType(TransactionType.CREDIT);
        transactionRequestDTO.setDescription("Test");
    }

    @Test
    public void createTransaction_Ok() throws Exception {

        Account account = mock(Account.class);
        account.setCurrency(CurrencyType.EURO);
        account.setBalance(0.00);
        account.setAccountNumber("10");

        when(accountService.getAccount("10")).thenReturn(Optional.of(account));
        when(account.getCurrency()).thenReturn(CurrencyType.EURO);
        when(account.getBalance()).thenReturn(100.00);
        when(account.getAccountNumber()).thenReturn("10");

        TransactionDTO _transactionDTO = transactionService.setTransaction(transactionRequestDTO);

        assertEquals(_transactionDTO.getAccountNumber(), transactionRequestDTO.getAccountNumber());
    }

}
