package com.backend.controller;

import com.backend.service.IAccountService;
import com.backend.service.impl.AccountServiceImpl;
import com.entity.Account;

import java.util.List;

public class AccountController {
    private IAccountService service;

    public AccountController() {
        this.service = new AccountServiceImpl();
    }

    public List<Account> findAll() {
        return service.findAll();
    }

    public boolean create(Account account) {
        return service.create(account);
    }

    public boolean update(int id, String username) {
        return service.update(id, username);
    }

    public boolean delete(int id) {
        return service.delete(id);
    }
}
