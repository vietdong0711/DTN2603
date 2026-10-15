package com.example.buoi1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

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

//    @Column(name = "created_date", length = 20)
//    @CreationTimestamp// default now()
//    private Date createdDate;
//
//
//    @Column(name = "updated_date", length = 20)
//    @UpdateTimestamp//
//    private Date updatedDate;// thời gian update gần nhất


    private String description;

//    @OneToMany(mappedBy = "department")
//    private List<Account> accounts;// ds account thuoc department này

    // n + 1 query
}
