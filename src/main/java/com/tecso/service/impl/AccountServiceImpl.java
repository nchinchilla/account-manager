package com.tecso.service.impl;

import com.tecso.exception.AccountException;
import com.tecso.exception.AccountNotCreatedException;
import com.tecso.dto.AccountDTO;
import com.tecso.exception.AccountNotFoundException;
import com.tecso.repository.AccountRepository;
import com.tecso.entity.Account;
import com.tecso.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public AccountDTO createAccount(Account account) {
        AccountDTO accountDTO;

        log.info("Creating new account, request data: {}", account );
        try {
            if (!accountRepository.findById(account.getAccountNumber()).isPresent()) {
                accountRepository.save(account);

                accountDTO = new AccountDTO(account.getAccountNumber(), account.getCurrency(),
                        account.getBalance(), "CREATED SUCCESSFULLY");
            } else {
                accountDTO = new AccountDTO(account.getAccountNumber(), account.getCurrency(),
                        account.getBalance(), "ACCOUNT NUMBER ALREADY EXISTS");
            }
            log.info("account created: {}", accountDTO);
            return accountDTO;
        }catch (Exception e){
            log.info("Account-Manager: Error in createAccount: {}", e.getMessage());
            throw new AccountNotCreatedException("Error trying to create an account");
        }
    }

    @Override
    public Boolean isAccountNumberIsAlreadyInUse(String accountNumber) {
        return accountRepository.findById(accountNumber)==null ? false : true;
    }

    @Override
    public AccountDTO deleteAccount(String accountNumber) {
        AccountDTO accountDTO;

        log.info("Deleting account, request data: {}", accountNumber);
        try{
            Account account = accountRepository.findById(accountNumber).orElse(null);

            if (account != null && account.getTransaction().size() <= 0) {
                accountRepository.deleteById(accountNumber);
                accountDTO = new AccountDTO(accountNumber, null, null, "DELETE ACCOUNT SUCCESSFULLY");
            } else{
                accountDTO = new AccountDTO(accountNumber, null, null, "DELETE REJECTED");
            }

            return accountDTO;
        }catch (Exception e){
            log.info("Account-Manager: Error in deleteAccount: {}", e.getMessage());
            throw new AccountException("Error trying to delete an account");
        }
    }

    @Override
    public List<Account> listAccount() {
        List<Account> accountList = new ArrayList<>();
        try{
            log.info("list account");
            accountList = accountRepository.findAll();
            return accountList;
        }catch (Exception e){
            log.info("Account-Manager: Error in listAccount: {}", e.getMessage());
            throw new AccountException("Error trying to list accounts");
        }
    }

    @Override
    public Account listAccountByAccountNumber(String accountNumber) {
        AccountDTO accountDTO = new AccountDTO();

        log.info("Getting information for accountNumber: {}", accountNumber);
        Optional<Account> response = accountRepository.findById(accountNumber);

        return response.orElseThrow(()-> new AccountNotFoundException("Account not exists"));

    }

    @Override
    public Optional<Account> getAccount(String accountNumber) {
        try{
            log.info("Getting information for accountNumber: {}", accountNumber);
            Optional<Account> response = accountRepository.findById(accountNumber);
            return response;
        }catch (Exception e){
            log.info("Account-Manager: Error in getAccount: {}", e.getMessage());
            throw new AccountException("Error trying to get an account");
        }
    }
}
