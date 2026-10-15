package com.example.buoi1.service.impl;

import com.example.buoi1.entity.Demo;
import com.example.buoi1.repository.IDemoRepository;
import com.example.buoi1.service.IDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements IDemoService {

    @Autowired
    private IDemoRepository demoRepository;

    @Override
    public void create(Demo demo) {
        demoRepository.save(demo);
    }
}
