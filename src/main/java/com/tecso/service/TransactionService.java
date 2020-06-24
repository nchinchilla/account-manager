package com.tecso.service;


import com.tecso.dto.TransactionRequestDTO;
import com.tecso.dto.TransactionDTO;

public interface TransactionService {

    TransactionDTO setTransaction(TransactionRequestDTO transaction);
}
