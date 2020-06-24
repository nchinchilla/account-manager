package com.tecso.controller;

import com.tecso.dto.AccountDTO;
import com.tecso.dto.TransactionDTO;
import com.tecso.dto.TransactionRequestDTO;
import com.tecso.entity.Account;
import com.tecso.enums.CurrencyType;
import com.tecso.enums.TransactionType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AccountControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;


    @Test
    public void create_accountOK(){


        //Mock Payload
       Account account = new Account();
       account.setBalance(0.00);
       account.setAccountNumber("1");
       account.setCurrency(CurrencyType.ARS);


        ResponseEntity<AccountDTO> response = testRestTemplate.postForEntity("/tecso/account", account, AccountDTO.class);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));

        AccountDTO accountDTO = response.getBody();
        assertThat(accountDTO.getAccountNumber(), is(account.getAccountNumber()));

    }

    @Test
    public void delete_account_Ok(){

        //Mock
        String accountNumber = "1";

        ResponseEntity<AccountDTO> response = testRestTemplate.postForEntity("/tecso/account/delete/2", null,AccountDTO.class);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));

        AccountDTO accountDTO = response.getBody();
        assertThat(accountDTO.getMessage(), is("DELETE ACCOUNT SUCCESSFULLY"));

    }

    @Test
    public void create_transaction_with_more_then_200_characters(){


        //Mock Payload
        String description = new Random().ints(205, 65, 101)
                .mapToObj(i -> String.valueOf((char) i))
                .collect(Collectors.joining());

        //Mock
        TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTO();
        transactionRequestDTO.setAccountNumber("5");
        transactionRequestDTO.setTransactionType(TransactionType.CREDIT);
        transactionRequestDTO.setDescription(description);
        transactionRequestDTO.setAmount(100.00);
        transactionRequestDTO.setCurrency(CurrencyType.DOLAR);


        ResponseEntity<TransactionDTO> response = testRestTemplate.postForEntity("/tecso/transaction", transactionRequestDTO, TransactionDTO.class);

        assertThat(response.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));

        TransactionDTO transactionDTO = response.getBody();


    }

    @Test
    public void delete_account_with_transactions_Error(){

        //Mock
        String accountNumber = "3";

        //testRestTemplate.delete("tecso/account/delete/{accountNumber}", accountNumber);
        ResponseEntity<AccountDTO> response = testRestTemplate.postForEntity("/tecso/account/delete/3", null, AccountDTO.class);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));

        AccountDTO accountDTO = response.getBody();
        assertThat(accountDTO.getAccountNumber(), is(accountNumber));

    }

    @Test
    public void create_transaction_with_non_existent_account(){

        //Mock
        TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTO();
        transactionRequestDTO.setAccountNumber("4");
        transactionRequestDTO.setTransactionType(TransactionType.CREDIT);
        transactionRequestDTO.setDescription("TEST");
        transactionRequestDTO.setAmount(100.00);
        transactionRequestDTO.setCurrency(CurrencyType.DOLAR);


        ResponseEntity<TransactionDTO> response = testRestTemplate.postForEntity("/tecso/transaction", transactionRequestDTO, TransactionDTO.class);

        assertThat(response.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));


    }

    @Test
    public void create_transaction_Ok(){

        //Mock
        TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTO();
        transactionRequestDTO.setAccountNumber("5");
        transactionRequestDTO.setTransactionType(TransactionType.CREDIT);
        transactionRequestDTO.setDescription("TEST");
        transactionRequestDTO.setAmount(100.00);
        transactionRequestDTO.setCurrency(CurrencyType.DOLAR);


        ResponseEntity<TransactionDTO> response = testRestTemplate.postForEntity("/tecso/transaction", transactionRequestDTO, TransactionDTO.class);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));

        TransactionDTO transactionDTO = response.getBody();
        assertThat(transactionDTO.getAccountNumber(), is(transactionRequestDTO.getAccountNumber()));

    }

    @Test
    public void create_transaction_with_existent_account_and_different_currency(){

        //Mock
        TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTO();
        transactionRequestDTO.setAccountNumber("6");
        transactionRequestDTO.setTransactionType(TransactionType.CREDIT);
        transactionRequestDTO.setDescription("TEST");
        transactionRequestDTO.setAmount(100.00);
        transactionRequestDTO.setCurrency(CurrencyType.ARS);


        ResponseEntity<TransactionDTO> response = testRestTemplate.postForEntity("/tecso/transaction", transactionRequestDTO, TransactionDTO.class);

        assertThat(response.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @Test
    public void create_transaction_over_the_limit_euro(){
        //Mock
        TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTO();
        transactionRequestDTO.setAccountNumber("7");
        transactionRequestDTO.setTransactionType(TransactionType.DEBIT);
        transactionRequestDTO.setDescription("TEST");
        transactionRequestDTO.setAmount(100.00);
        transactionRequestDTO.setCurrency(CurrencyType.EURO);


        ResponseEntity<TransactionDTO> response = testRestTemplate.postForEntity("/tecso/transaction", transactionRequestDTO, TransactionDTO.class);

        assertThat(response.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @Test
    public void create_transaction_over_the_limit_dolar(){
        //Mock
        TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTO();
        transactionRequestDTO.setAccountNumber("8");
        transactionRequestDTO.setTransactionType(TransactionType.DEBIT);
        transactionRequestDTO.setDescription("TEST");
        transactionRequestDTO.setAmount(100.00);
        transactionRequestDTO.setCurrency(CurrencyType.DOLAR);


        ResponseEntity<TransactionDTO> response = testRestTemplate.postForEntity("/tecso/transaction", transactionRequestDTO, TransactionDTO.class);

        assertThat(response.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));

    }

    @Test
    public void create_transaction_over_the_limit_ars(){
        //Mock
        TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTO();
        transactionRequestDTO.setAccountNumber("9");
        transactionRequestDTO.setTransactionType(TransactionType.DEBIT);
        transactionRequestDTO.setDescription("TEST");
        transactionRequestDTO.setAmount(100.00);
        transactionRequestDTO.setCurrency(CurrencyType.ARS);


        ResponseEntity<TransactionDTO> response = testRestTemplate.postForEntity("/tecso/transaction", transactionRequestDTO, TransactionDTO.class);

        assertThat(response.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));
    }


}
