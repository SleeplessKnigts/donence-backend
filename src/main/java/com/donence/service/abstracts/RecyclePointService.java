package com.donence.service.abstracts;

import com.donence.model.RecyclePoint;

import java.util.List;

public interface RecyclePointService {
    RecyclePoint add(RecyclePoint recyclePoint);
    List<RecyclePoint> getRecyclePoints();
}
