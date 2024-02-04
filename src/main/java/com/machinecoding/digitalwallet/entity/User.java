package com.machinecoding.digitalwallet.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Data
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = -6463599719449004898L;
    private String id;
    private String name;

    public User(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }
}