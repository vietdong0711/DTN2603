package com.backend.controller;

import com.backend.service.IPositionService;
import com.backend.service.impl.PositonServiceImpl;
import com.entity.Position;

import java.util.List;

public class PositionController {
    private IPositionService service;

    public PositionController() {
        this.service = new PositonServiceImpl();
    }

    public List<Position> findAll() {
        return service.findAll();
    }
}
