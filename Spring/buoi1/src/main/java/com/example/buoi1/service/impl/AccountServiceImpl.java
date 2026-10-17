package com.example.buoi1.service.impl;

import com.example.buoi1.dto.AccountDTO;
import com.example.buoi1.entity.Account;
import com.example.buoi1.entity.Department;
import com.example.buoi1.exception.BusinessException;
import com.example.buoi1.from.AccountForm;
import com.example.buoi1.from.AccountSearchForm;
import com.example.buoi1.repository.IAccountRepository;
import com.example.buoi1.repository.IDepartmentRepository;
import com.example.buoi1.service.IAccountService;
import com.example.buoi1.specification.AccountCustomSecification;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private IDepartmentRepository departmentRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Page<AccountDTO> findAll(AccountSearchForm searchForm, Pageable pageable) {
        Specification<Account> where = Specification.unrestricted();// 1 = 1

        if (searchForm.getUsername() != null) {
            AccountCustomSecification usernameSearch = new AccountCustomSecification("username", searchForm.getUsername());
            where = where.and(usernameSearch);// where username like "?"
        }

        if (searchForm.getEmail() != null) {
            AccountCustomSecification emailSearch = new AccountCustomSecification("email", searchForm.getEmail());
            where = where.and(emailSearch);// where email like "?"
        }

        if (searchForm.getDepName() != null) {
            AccountCustomSecification depNameSearch = new AccountCustomSecification("depName", searchForm.getDepName());
            where = where.and(depNameSearch);// where dep.department_name like "?"
        }
        //  where 1=1 and username like "?" and email like "?" and dep.department_name like "?"

        Page<Account> accounts = accountRepository.findAll(where, pageable);

        // chuyển Page<Account>  -> Page<AccountDTO>

        return accounts.map(account -> modelMapper.map(account, AccountDTO.class));
    }

    @Override
    public AccountDTO findById(Integer id) {
        Account account = accountRepository.findById(id).orElse(null);
        if (Objects.isNull(account)) {
            throw new RuntimeException("ID not found");
        }
        return modelMapper.map(account, AccountDTO.class);
    }

    @Override
    @Transactional
    public void create(AccountForm form) {
        //validation
        if (accountRepository.existsByUsernameAndIdNot(form.getUsername(), null)) {
            throw new BusinessException("Username already exists", 500);
        }

        if (accountRepository.existsByEmailAndIdNot(form.getEmail(), null)) {
            throw new BusinessException("Email already exists", 500);
        }

        Department department = departmentRepository.findById(form.getDepartmentId()).orElse(null);
        if (Objects.isNull(department)) {
            // tiến hành tạo 1 department mới theo id mới đưa vào
            Department newDepartment = new Department();
            newDepartment.setName("Department new");
            department = departmentRepository.save(newDepartment);
            throw new RuntimeException();
        }

        // chuyển form --> entity
        Account account = new Account();
        account.setUsername(form.getUsername());
        account.setFullname(form.getFullname());
        account.setEmail(form.getEmail());
        account.setPassword(form.getPassword());

        account.setDepartment(department);
        // khi lưu dữ liệu vào dB thì phải lưu entity chứ ko lưu form
        accountRepository.save(account);
    }

    @Override
    public void updateById(Integer id, AccountForm form) {
        // lay account tu id dua vao
        Account account = accountRepository.findById(id).orElse(null);
        if (Objects.isNull(account)) {
            throw new BusinessException("ID not found", 500);
        }

        if (accountRepository.existsByEmailAndIdNot(form.getUsername(), id)) {
            throw new BusinessException("Username already exists", 500);
        }

        if (accountRepository.existsByEmailAndIdNot(form.getEmail(), id)) {
            throw new BusinessException("Email already exists", 500);
        }

        // chuyển departmentID ở form -> department
        Department department = departmentRepository.findById(form.getDepartmentId()).orElse(null);
        if (Objects.isNull(department)) {
            throw new BusinessException("DepartmentID not found", 500);
        }
        account.setUsername(form.getUsername());
        account.setFullname(form.getFullname());
        account.setEmail(form.getEmail());
        account.setPassword(form.getPassword());
        account.setDepartment(department);

        accountRepository.save(account);
    }

    @Override
    public void deleteById(Integer id) {
        if (!accountRepository.existsById(id)) {
            throw new RuntimeException("ID not found");
        }
        accountRepository.deleteById(id);
    }
}
