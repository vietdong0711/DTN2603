package com.example.buoi1.dto;

import com.example.buoi1.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    // 20 thuộc tính cần hiển thị
    private Integer id;
    private String username;
    private String fullname;
    private String email;
    private String departmentName;


//    public AccountDTO(Account account) {
//        this.id = account.getId();
//        this.username = account.getUsername();
//        this.fullname = account.getFullname();
//        this.email = account.getEmail();
//
//        String depName = Objects.nonNull(account.getDepartment())
//                ? account.getDepartment().getName() : "";
//        this.departmentName = depName;
//        // ít nhất 20 dòng để gán gtri
//    }
}
