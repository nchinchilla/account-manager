package com.tecso.exception;

public class TransactionNotCreatedException extends RuntimeException {
    public TransactionNotCreatedException(String message){ super(message); }
}
