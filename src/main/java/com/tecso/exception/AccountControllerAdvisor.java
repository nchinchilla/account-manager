package com.tecso.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class AccountControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<Object> handleAccountNotFound (AccountNotFoundException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("name","ACCOUNT-MANAGER");
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Account Not Found");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccountException.class)
    public ResponseEntity<Object> handleAccount (AccountException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("name","ACCOUNT-MANAGER");
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @ExceptionHandler(AccountNotCreatedException.class)
    public ResponseEntity<Object> handleAccountNotCreated (AccountNotCreatedException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("name","ACCOUNT-MANAGER");
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Account not created");

        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @ExceptionHandler(TransactionNotCreatedException.class)
    public ResponseEntity<Object> handleTransactionNotCreated (TransactionNotCreatedException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("MS","ACCOUNT-MANAGER");
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Transaction not created");

        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
