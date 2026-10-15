package com.example.buoi1.from;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CodePointLength;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountForm {
    @NotBlank(message = "Username phải có giá trị")
    @Length(max = 100, min = 6, message = "Username phải từ 6 đến 100 ký tự")
    private String username;

    @NotBlank(message = "Fullname phải có giá trị")
    @Length(max = 100, min = 6, message = "Fullname phải từ 6 đến 100 ký tự")
    private String fullname;

    @NotBlank(message = "Email phải có giá trị")
    @Length(max = 100, min = 6, message = "Email phải từ 6 đến 100 ký tự")
    @Email(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "Email ko đúng định dạng")
    private String email;


    @CodePointLength(min = 6, max = 100)
    private String password;
    private Integer departmentId;
}
