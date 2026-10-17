package com.example.buoi1.service;

import com.example.buoi1.dto.AccountDTO;
import com.example.buoi1.from.AccountForm;
import com.example.buoi1.from.AccountSearchForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAccountService {
    Page<AccountDTO> findAll(AccountSearchForm searchForm, Pageable pageable);

    AccountDTO findById(Integer id);

    void create(AccountForm form);

    void updateById(Integer id, AccountForm form);

    void deleteById(Integer id);
}
