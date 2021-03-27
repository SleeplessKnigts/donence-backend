package com.donence.service.abstracts;

import com.donence.dto.request.CollectionEventDto;
import com.donence.model.CollectionEvent;

import java.util.List;

public interface CollectionEventService {
    List<CollectionEvent> getCollectionEventList();
    List<CollectionEventDto> getCollectionEventListDtos();
    void addCollectionEvent(CollectionEvent collectionEvent);
}
