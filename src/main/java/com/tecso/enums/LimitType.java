package com.tecso.enums;

public enum LimitType {
    ARS (-1000.000), DOL(-300.000), EUR(-150.00);

    private Double limit;

    LimitType(Double limit){
        this.limit = limit;
    }

    public Double getLimit(){
        return limit;
    }
}
