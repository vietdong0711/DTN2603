package com.example.buoi1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "department")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id     // khóa chính
    @Column(name = "department_id")   // mapping với cột department_id ở DB
    @GeneratedValue(strategy = GenerationType.IDENTITY)// auto_increment
    private Integer id;

    @Column(name = "department_name", nullable = false, length = 100)// not null,  độ dài = 100
    private String name;

    @Column(name = "quantity_account")
    private int quantity;
}
