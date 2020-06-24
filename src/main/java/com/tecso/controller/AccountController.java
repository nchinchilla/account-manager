package com.tecso.controller;

import com.tecso.entity.Account;
import com.tecso.dto.AccountDTO;
import com.tecso.dto.TransactionRequestDTO;
import com.tecso.dto.TransactionDTO;
import com.tecso.service.AccountService;
import com.tecso.service.TransactionService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value="/tecso/v1")
public class AccountController {

    private static final Logger LOG = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @ApiOperation(value = "Create new account", response = AccountDTO.class)
    @ApiResponses({@ApiResponse(code=200, message = "Success", response = AccountDTO.class),
    @ApiResponse(code=500, message = "Error")})
    @PostMapping(value = "tecso/account")
    public ResponseEntity<AccountDTO> createAccount(@RequestBody Account account){
        return  ResponseEntity.ok().body(accountService.createAccount(account));
    }

    @ApiOperation(value = "Delete account", response = AccountDTO.class)
    @ApiResponses({@ApiResponse(code=200, message = "Success", response = AccountDTO.class),
    @ApiResponse(code=500, message = "Error")})
    @PostMapping("tecso/account/delete/{accountNumber}")
    public ResponseEntity<AccountDTO> deleteAccount(@PathVariable("accountNumber") String accountNumber){
       return ResponseEntity.ok().body(accountService.deleteAccount(accountNumber));
    }

    @ApiOperation(value = "Create transaction", response = TransactionDTO.class)
    @ApiResponses({@ApiResponse(code=200, message = "Success", response = TransactionDTO.class),
    @ApiResponse(code=500, message = "Error")})
    @PostMapping("tecso/transaction")
    public ResponseEntity<TransactionDTO> makeTransaction(@RequestBody TransactionRequestDTO transaction){
        return ResponseEntity.ok().body(transactionService.setTransaction(transaction));
    }

    @ApiOperation(value = "List transaction by account number", response = Account.class)
    @ApiResponses({@ApiResponse(code=200, message = "Success", response = Account.class)})
    @GetMapping("tecso/account/{accountNumber}")
    public ResponseEntity<List<Account>> getTransactionByAcccount(@PathVariable("accountNumber") String accountNumber){
        return ResponseEntity.ok().body(accountService.listAccount(accountNumber));
    }





}
