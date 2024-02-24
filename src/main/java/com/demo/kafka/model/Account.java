package com.demo.kafka.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Account implements Serializable {

    private int accountNumber;
    private String accountName;
    private String accountSymbol;
}
