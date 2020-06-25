package com.tecso.entity;

import com.tecso.enums.CurrencyType;
import com.tecso.enums.TransactionType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@Table(name="limit_currency")
public class Limit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private long id;

    @ApiModelProperty(value = "Currency")
    @Column(name="currency")
    CurrencyType currencyType;

    @ApiModelProperty(value = "Limit of transaction")
    @Column(name="amount")
    Double amount;
}
