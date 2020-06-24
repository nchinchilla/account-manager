package com.tecso.dto;

import com.tecso.enums.CurrencyType;
import com.tecso.enums.TransactionType;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequestDTO {

    @ApiModelProperty(value = "Account number")
    private String accountNumber;

    @ApiModelProperty(value = "Date of transaction")
    private Date date;

    @ApiModelProperty(value = "Transaction type")
    private TransactionType transactionType;

    @ApiModelProperty(value = "Description")
    private String description;

    @ApiModelProperty(value = "Amount")
    private Double amount;

    @ApiModelProperty(value = "Currency")
    private CurrencyType currency;

}
