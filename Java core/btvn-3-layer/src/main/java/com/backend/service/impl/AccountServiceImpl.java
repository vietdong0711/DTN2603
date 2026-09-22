package com.backend.service.impl;

import com.backend.repository.IAccountRepository;
import com.backend.repository.impl.AccountRepositoryImpl;
import com.backend.service.IAccountService;
import com.entity.Account;

import java.util.List;

public class AccountServiceImpl implements IAccountService {
    private IAccountRepository repository;

    public AccountServiceImpl() {
        repository = new AccountRepositoryImpl();
    }

    @Override
    public List<Account> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean create(Account account) {
        return repository.create(account);
    }

    @Override
    public boolean update(int id, String username) {
        return repository.update(id, username);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id);
    }

    @Override
    public boolean checkUsernameExists(String username, Integer id) {
        return repository.checkUsernameExists(username, id);
    }

    @Override
    public boolean checkEmailExists(String email, Integer id) {
        return repository.checkEmailExists(email, id);
    }
}
