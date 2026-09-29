package com.backend.service.impl;

import com.backend.repository.IDepartmentRepository;
import com.backend.repository.impl.DepartmentRepositoryImpl;
import com.backend.service.IDepartmentService;
import com.backend.service.ImportCSV;
import com.context.DepartmentContext;
import com.entity.Department;

import java.util.List;

public class DepartmentServiceImpl implements IDepartmentService, ImportCSV<Department, DepartmentContext> {
    private IDepartmentRepository repository;

    public DepartmentServiceImpl() {
        this.repository = new DepartmentRepositoryImpl();
    }

    @Override
    public List<Department> findAll() {
        return repository.findAll();
    }

    @Override
    public void validation(String line, List<Department> entities, List<String> lineErrors, DepartmentContext context) {

    }

    @Override
    public void saveAll(List<Department> entities) {

    }

    @Override
    public void exportErrors(String header, List<String> lineErrors, String pathErrors) {

    }
}
