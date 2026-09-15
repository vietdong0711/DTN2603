package com.backend.repository;

import com.entity.Department;

import java.util.List;

public interface IDepartmentRepository {
    List<Department> findAll();
}
