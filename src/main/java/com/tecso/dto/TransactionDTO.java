package com.tecso.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tecso.enums.TransactionType;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDTO {

    @ApiModelProperty(value = "Account number")
    private String accountNumber;

    @ApiModelProperty(value = "Amount")
    private Double amount;

    @ApiModelProperty(value = "Transaction Type")
    private TransactionType transactionType;

    @ApiModelProperty(value = "Number")
    private String message;
}
