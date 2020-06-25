package com.tecso.service;


import com.tecso.entity.Account;
import com.tecso.dto.AccountDTO;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    public AccountDTO createAccount(Account account);

    public AccountDTO deleteAccount(String accountNumber);

    public List<Account> listAccount();

    public Optional<Account> getAccount(String accountNumber);

    public Boolean isAccountNumberIsAlreadyInUse(String accountNumber);

    public Account listAccountByAccountNumber(String accountNumber);

}
