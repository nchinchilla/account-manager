package com.tecso.entity;


import com.tecso.enums.TransactionType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@ApiModel(description = "Representation of Account")
@Table(name="transaction")
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ApiModelProperty(value = "Date of transaction")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date", nullable = false, insertable = true, updatable = false)
    Date date;

    @NotNull
    @ApiModelProperty(value = "Type of transaction")
    @Column(name="transaction")
    @Enumerated(EnumType.STRING)
    TransactionType transactionType;

    @Size(max = 200, message = "Please enter only 200 characters")
    @ApiModelProperty(value = "Description of transaction")
    @Column(name="description")
    String description;

    @NotNull
    @ApiModelProperty(value = "Amount of transaction")
    @Column(name="amount")
    Double amount;

}
