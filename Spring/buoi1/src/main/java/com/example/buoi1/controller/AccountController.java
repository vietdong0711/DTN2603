package com.example.buoi1.controller;

import com.example.buoi1.dto.AccountDTO;
import com.example.buoi1.entity.Account;
import com.example.buoi1.from.AccountForm;
import com.example.buoi1.from.AccountSearchForm;
import com.example.buoi1.service.IAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@Validated
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @GetMapping
    public ResponseEntity<Page<AccountDTO>> findAll(AccountSearchForm searchForm, Pageable pageable) {
        return new ResponseEntity<>(accountService.findAll(searchForm, pageable), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public AccountDTO findById(@PathVariable Integer id) {
        return accountService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Void> createAccount(@RequestBody @Valid AccountForm form) {
         accountService.create(form);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateById(@PathVariable Integer id, @RequestBody AccountForm form) {
        accountService.updateById(id, form);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable Integer id) {
        accountService.deleteById(id);
    }
}
