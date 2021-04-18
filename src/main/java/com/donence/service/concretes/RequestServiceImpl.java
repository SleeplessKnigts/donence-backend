package com.donence.service.concretes;

import com.donence.model.Request;
import com.donence.model.User;
import com.donence.repository.RequestRepository;
import com.donence.service.abstracts.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    RequestRepository requestRepository;

    @Override
    public List<Request> getRequestsByActiveOrderByCreationDateDesc(Boolean isActive) {
        return requestRepository.findByIsActiveOrderByCreationDateDesc(isActive);
    }

    @Override
    public List<Request> getRequestsByActiveAndIssuerOrderByCreationDateDesc(Boolean isActive, User issuer) {
        return requestRepository.findByIsActiveAndIssuerOrderByCreationDateDesc(isActive, issuer);
    }

    @Override
    public List<Request> getRequestsByIssuerOrderByCreationDateDesc(User issuer) {
        return requestRepository.findByIssuerOrderByCreationDateDesc(issuer);
    }

    @Override
    public List<Request> getRequestOrderByCreationDateDesc() {
        return requestRepository.findAllByOrderByCreationDateDesc();
    }

    @Override
    public void completeRequest(Integer id) {
        Request request = requestRepository.getOne(id);
        request.setActive(false);
        requestRepository.save(request);
    }
}
