package com.tecso.entity;

import com.tecso.enums.CurrencyType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@Data
@Entity
@Table(name="account")
public class Account {


    @Id
    @NotNull
    @ApiModelProperty(value = "Identifier", hidden = true)
    @Column(name= "account_number", unique = true)
    public String accountNumber;

    @NotNull
    @ApiModelProperty(value = "Currency of account")
    @Column(name = "currency")
    @Enumerated(EnumType.STRING)
    private CurrencyType currency;

    @ApiModelProperty(value = "balance of account")
    @Column(name="balance")
    private Double balance = 0.00;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="account_number", nullable=false)
    private List<Transaction> transaction;
}
