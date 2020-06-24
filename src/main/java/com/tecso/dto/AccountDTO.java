package com.tecso.dto;

import com.tecso.enums.CurrencyType;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

    @ApiModelProperty(value = "Account number")
    String accountNumber;

    @ApiModelProperty(value = "Currency")
    CurrencyType currency;

    @ApiModelProperty(value = "balance")
    Double balance;

    @ApiModelProperty(value = "message")
    String message;

}
