package com.donence.service;

import com.donence.model.RecyclePoint;
import com.donence.repository.RecyclePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecyclePointServiceImpl implements RecyclePointService{

    @Autowired
    RecyclePointRepository recyclePointRepository;

    @Override
    public RecyclePoint add(RecyclePoint recyclePoint) {
        return recyclePointRepository.save(recyclePoint);
    }
}
