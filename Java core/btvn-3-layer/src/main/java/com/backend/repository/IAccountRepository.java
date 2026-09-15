package com.backend.repository;

import com.entity.Account;

import java.util.List;

public interface IAccountRepository {
    List<Account> findAll();
    boolean create(Account account);
    boolean update(int id, String username);
    boolean delete(int id);
}
