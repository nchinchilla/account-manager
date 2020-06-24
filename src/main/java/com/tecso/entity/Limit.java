//package com.tecso.entity;
//
//import com.tecso.enums.CurrencyType;
//import com.tecso.enums.TransactionType;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//import org.hibernate.annotations.CreationTimestamp;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//import java.util.Date;
//
//@Data
//@Entity
//@Table(name="limit")
//public class Limit {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    private long id;
//
//    @ApiModelProperty(value = "Currency")
//    @Column(name="currency")
//    CurrencyType currencyType;
//
//    @NotNull
//    @ApiModelProperty(value = "Limit of transaction")
//    @Column(name="limit")
//    @Enumerated(EnumType.STRING)
//    Double limit;
//}
