package com.backend.controller;

import com.backend.service.IDepartmentService;
import com.backend.service.impl.DepartmentServiceImpl;
import com.entity.Department;

import java.util.List;

public class DepartmentController {
    private IDepartmentService service;

    public DepartmentController() {
        this.service = new DepartmentServiceImpl();
    }

    public List<Department> findAll() {
        return service.findAll();
    }
}
