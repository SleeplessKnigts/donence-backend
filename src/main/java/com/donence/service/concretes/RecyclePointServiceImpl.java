package com.donence.service.concretes;

import com.donence.model.RecyclePoint;
import com.donence.repository.RecyclePointRepository;
import com.donence.service.abstracts.RecyclePointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecyclePointServiceImpl implements RecyclePointService {

    @Autowired
    RecyclePointRepository recyclePointRepository;

    @Override
    public RecyclePoint add(RecyclePoint recyclePoint) {
        return recyclePointRepository.save(recyclePoint);
    }

    @Override
    public List<RecyclePoint> getRecyclePoints() {
        return recyclePointRepository.findAll();
    }
}
