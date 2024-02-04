package com.machinecoding.digitalwallet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Transaction implements Serializable {

    @Serial
    private static final long serialVersionUID = 5388574071969603742L;
    private String sender;
    private String receiver;
    private BigDecimal amount;
    private LocalDateTime datetime;
    private String message;

    @Override
    public String toString() {
        return "Transaction : From = " + sender + ", to = " + receiver + ", of amount = " + amount + ",on date-time " +
                "=" + datetime + ". " + "Summary: " + message;
    }
}
