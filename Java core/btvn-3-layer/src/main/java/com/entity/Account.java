package com.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private int id;
    private String username;
    private String fullName;
    private String email;
    private Department department;
    private Position position;
    private LocalDate createDate;

    public Account(int id, String username, String fullName, String email, Department department, Position position) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.department = department;
        this.position = position;
    }
}
