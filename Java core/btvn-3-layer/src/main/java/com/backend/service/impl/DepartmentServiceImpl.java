package com.backend.service.impl;

import com.backend.repository.IDepartmentRepository;
import com.backend.repository.impl.DepartmentRepositoryImpl;
import com.backend.service.IDepartmentService;
import com.entity.Department;

import java.util.List;

public class DepartmentServiceImpl implements IDepartmentService {
    private IDepartmentRepository repository;

    public DepartmentServiceImpl() {
        this.repository = new DepartmentRepositoryImpl();
    }

    @Override
    public List<Department> findAll() {
        return repository.findAll();
    }
}
