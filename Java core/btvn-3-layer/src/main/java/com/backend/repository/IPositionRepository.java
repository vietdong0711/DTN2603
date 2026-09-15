package com.backend.repository;

import com.entity.Position;

import java.util.List;

public interface IPositionRepository {
    List<Position> findAll();
}
