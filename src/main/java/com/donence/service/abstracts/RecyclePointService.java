package com.donence.service.abstracts;

import com.donence.dto.request.RecyclePointDto;
import com.donence.model.RecyclePoint;

import java.util.List;

public interface RecyclePointService {
    RecyclePoint getPointById(Integer id);

    RecyclePoint add(RecyclePoint recyclePoint);

    List<RecyclePoint> getRecyclePoints();

    List<RecyclePointDto> getRecyclePointDtos();

    void delete(RecyclePoint recyclePoint);
}
