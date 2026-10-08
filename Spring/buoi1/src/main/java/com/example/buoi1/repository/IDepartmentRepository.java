package com.example.buoi1.repository;

import com.example.buoi1.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

                                                        // object JPA ho tro,  kieu dư lieu cua khoa chinh
public interface IDepartmentRepository extends JpaRepository<Department,Integer> {
}
