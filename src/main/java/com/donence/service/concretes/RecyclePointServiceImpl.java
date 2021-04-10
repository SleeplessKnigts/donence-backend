package com.donence.service.concretes;

import com.donence.dto.request.RecyclePointDto;
import com.donence.model.RecyclePoint;
import com.donence.repository.RecyclePointRepository;
import com.donence.service.abstracts.RecyclePointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecyclePointServiceImpl implements RecyclePointService {

    @Autowired
    RecyclePointRepository recyclePointRepository;

    @Override
    public RecyclePoint getPointById(Integer id) {
        Optional<RecyclePoint> recyclePoint = recyclePointRepository.findById(id);
        return recyclePoint.orElse(null);
    }

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
                    new RecyclePointDto(recyclePoint.getRecyclePointId(), recyclePoint.getRecyclePointDetail(), recyclePoint.getRecyclePointLatitude(),
                            recyclePoint.getRecyclePointLongitude(), recyclePoint.getRecyclePointPlaceType()));
        }
        return recyclePointDtos;
    }

    @Override
    public void delete(RecyclePoint recyclePoint) {
        recyclePointRepository.delete(recyclePoint);
    }
}
