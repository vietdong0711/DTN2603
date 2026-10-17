package com.example.buoi1.repository;

import com.example.buoi1.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface IAccountRepository extends JpaRepository<Account, Integer>, JpaSpecificationExecutor<Account> {

    boolean existsByUsernameAndIdNot(String username, Integer id);
    boolean existsByEmailAndIdNot(String email, Integer id);

    List<Account> findByUsernameContains(String username);
    // liệt kê n method chỉ để giải quyết bài toán tìm kiếm

}
