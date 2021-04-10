package com.donence.service.concretes;

import com.donence.dto.request.CollectionEventDto;
import com.donence.model.CollectionEvent;
import com.donence.repository.CollectionEventRepository;
import com.donence.service.abstracts.CollectionEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CollectionEventServiceImpl implements CollectionEventService {
    @Autowired
    CollectionEventRepository collectionEventRepository;

    @Override
    public List<CollectionEvent> getCollectionEventList() {
        return collectionEventRepository.findAll();
    }

    @Override
    public List<CollectionEventDto> getCollectionEventListDtos() {
        List<CollectionEvent> collectionEvents = collectionEventRepository.findAll();
        List<CollectionEventDto> collectionEventDtos = new ArrayList<>();

        for (CollectionEvent collectionEvent : collectionEvents) {
            collectionEventDtos.add(new CollectionEventDto(collectionEvent.getCollectionEventId(), collectionEvent.getMaterialType(), collectionEvent.getEventDetail(),
                    collectionEvent.getEventLatitude(), collectionEvent.getEventLongitude(), collectionEvent.getEventDate()));
        }
        return collectionEventDtos;
    }

    @Override
    public void addCollectionEvent(CollectionEvent collectionEvent) {
        collectionEventRepository.save(collectionEvent);
    }

    @Override
    public void deleteCollectionEvent(CollectionEvent collectionEvent) {
        collectionEventRepository.delete(collectionEvent);
    }

    @Override
    public CollectionEvent getCollectionEventById(Integer collectionEventId) {
        Optional<CollectionEvent> collectionEvent = collectionEventRepository.findById(collectionEventId);
        return collectionEvent.orElse(null);
    }
}
