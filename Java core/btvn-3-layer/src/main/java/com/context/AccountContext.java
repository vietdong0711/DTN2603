package com.context;

import com.entity.Account;
import com.entity.Department;
import com.entity.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountContext {
    private Map<String, Account> mapByUsername;
    private Map<String, Account> mapByEmail;
    private List<Department> departments;
    private List<Position> positions;
}
