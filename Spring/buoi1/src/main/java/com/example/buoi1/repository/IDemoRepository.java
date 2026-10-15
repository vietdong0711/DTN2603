package com.example.buoi1.repository;

import com.example.buoi1.entity.Demo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDemoRepository extends JpaRepository<Demo,Integer> {
}
