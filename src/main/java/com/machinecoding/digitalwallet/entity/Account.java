package com.machinecoding.digitalwallet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account implements Serializable {
    @Serial
    private static final long serialVersionUID = 4023269820396131926L;
    private String accountNumber;
    private User user;
    private BigDecimal balance;
}
