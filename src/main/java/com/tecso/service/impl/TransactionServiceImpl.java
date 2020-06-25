package com.tecso.service.impl;

import com.tecso.dto.TransactionDTO;
import com.tecso.entity.Account;
import com.tecso.entity.Transaction;
import com.tecso.enums.CurrencyType;
import com.tecso.enums.TransactionType;
import com.tecso.exception.AccountNotFoundException;
import com.tecso.exception.TransactionNotCreatedException;
import com.tecso.dto.TransactionRequestDTO;
import com.tecso.repository.AccountRepository;

import com.tecso.service.AccountService;
import com.tecso.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.tecso.enums.LimitType.*;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {


    @Autowired
    AccountRepository accountRepository;

    @Override
    public TransactionDTO setTransaction(TransactionRequestDTO transactionRequestDTO) {

        Double currentBalance = 0.0;
        List<Transaction> transactionList = new ArrayList<>();
        Transaction transaction = new Transaction();
        TransactionDTO transactionDTO;
        log.info("Creating a new transaction for a account number: {}", transactionRequestDTO);

        try {
            Optional<Account> accountResponse = accountRepository.findById(transactionRequestDTO.getAccountNumber());

            if (accountResponse.isPresent()) {
                Account account = accountResponse.get();
                if ((transactionRequestDTO.getTransactionType().equals(TransactionType.DEBIT) &&
                        !checkLimit(account.getBalance(), transactionRequestDTO.getAmount(), account.getCurrency()))
                        || (!evaluateCurrency(transactionRequestDTO.getCurrency(), account.getCurrency()))) {
                    throw new TransactionNotCreatedException("Transaction not allowed");
                } else {
                    try {
                        account.setBalance(updateBalance(account.getBalance(), transactionRequestDTO.getAmount(), transactionRequestDTO.getTransactionType()));
                        transaction.setDescription(transactionRequestDTO.getDescription());
                        transaction.setAmount(round(transactionRequestDTO.getAmount(),2));
                        transaction.setTransactionType(transactionRequestDTO.getTransactionType());
                        transaction.setDate(transactionRequestDTO.getDate());
                        transactionList.add(transaction);
                        account.setTransaction(transactionList);

                        accountRepository.save(account);
                        return transactionDTO = new TransactionDTO(account.getAccountNumber(),
                                transaction.getAmount(), transaction.getTransactionType(), transaction.getDescription());
                    } catch (Exception e) {
                        log.error(e.getMessage());
                        throw new TransactionNotCreatedException("Error in validation");
                    }
                }
            } else {
                throw new AccountNotFoundException("The account it is not available for the current transaction");
            }
        }catch (Exception e){
            log.info("Account-Manager: Error in createAccount: {}", e.getMessage());
            throw new TransactionNotCreatedException("Error trying to create an transaction");
        }
    }

    private Boolean evaluateCurrency(CurrencyType transactionCurrency, CurrencyType currencyAccount){
        log.info("Account-manager: Compare both Currency: {}-{} ", transactionCurrency,currencyAccount);
        return transactionCurrency.equals(currencyAccount);
    }
    private Boolean checkLimit(Double currentBalance, Double amount, CurrencyType currencyType){
        log.info("Account-manager: checkLimit currentBalance - {}, amount {}, currencyType {}",currentBalance, amount, currencyType);

        Double result = currentBalance - amount;

        if(currencyType.equals(CurrencyType.ARS) ){
            return Double.compare(result, ARS.getLimit()) >= 0 ? true:false;
        }else if(currencyType.equals(CurrencyType.DOLAR)){
            return Double.compare(result, DOL.getLimit()) >= 0 ? true : false;
        }else if(currencyType.equals(CurrencyType.EURO)) {
            return Double.compare(result, EUR.getLimit()) >= 0 ? true : false;
        }
        return false;
    }
    private Double updateBalance(Double currentBalance, Double amount, TransactionType transactionType ){

        log.info("Account-manager: checkLimit currentBalance - {}, amount {}, transactionType {}",currentBalance, amount, transactionType);
        Double balance;
        switch(transactionType){
            case DEBIT:
                balance = currentBalance - amount;
                return round(balance,2);
            case CREDIT:
                balance = currentBalance + amount;
                return round(balance,2);
            default:
                return 0.00;
        }
    }

    public static Double round(Double d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Double.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }
}
