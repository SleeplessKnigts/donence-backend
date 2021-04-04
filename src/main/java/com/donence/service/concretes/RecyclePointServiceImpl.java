package com.donence.service.concretes;

import com.donence.dto.request.RecyclePointDto;
import com.donence.model.RecyclePoint;
import com.donence.repository.RecyclePointRepository;
import com.donence.service.abstracts.RecyclePointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
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

    @Override
    public List<RecyclePointDto> getRecyclePointDtos() {
        List<RecyclePoint> recyclePoints = recyclePointRepository.findAll();
        List<RecyclePointDto> recyclePointDtos = new ArrayList<>();

        for (RecyclePoint recyclePoint : recyclePoints) {
            recyclePointDtos.add(
                    new RecyclePointDto(recyclePoint.getRecyclePointDetail(), recyclePoint.getRecyclePointLatitude(),
                            recyclePoint.getRecyclePointLongitude(), recyclePoint.getRecyclePointPlaceType()));
        }
        return recyclePointDtos;
    }
}
