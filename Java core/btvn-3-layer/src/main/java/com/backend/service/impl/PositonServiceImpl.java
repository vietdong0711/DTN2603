package com.backend.service.impl;

import com.backend.repository.IPositionRepository;
import com.backend.repository.impl.PositionRepositoryImpl;
import com.backend.service.IPositionService;
import com.entity.Position;

import java.util.List;

public class PositonServiceImpl implements IPositionService {
    private IPositionRepository repository;

    public PositonServiceImpl() {
        this.repository = new PositionRepositoryImpl();
    }

    @Override
    public List<Position> findAll() {
        return repository.findAll();
    }
}
