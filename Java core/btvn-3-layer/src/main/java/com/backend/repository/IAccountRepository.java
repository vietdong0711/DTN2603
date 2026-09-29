package com.backend.repository;

import com.entity.Account;

import java.util.List;

public interface IAccountRepository {
    List<Account> findAll();
    boolean create(Account account);
    void createAccounts(List<Account> entities);
    boolean update(int id, String username);
    boolean delete(int id);
    boolean checkIdExists(int id);
    boolean checkUsernameExists(String username, Integer id);
    boolean checkEmailExists(String email, Integer id);

    boolean login(String username, String password);
}
