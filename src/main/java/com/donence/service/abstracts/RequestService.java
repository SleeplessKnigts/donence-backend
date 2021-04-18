package com.donence.service.abstracts;

import com.donence.model.Request;
import com.donence.model.User;

import java.util.List;

public interface RequestService{
    List<Request> getRequestsByActiveOrderByCreationDateDesc(Boolean isActive);

    List<Request> getRequestsByActiveAndIssuerOrderByCreationDateDesc(Boolean isActive, User issuer);

    List<Request> getRequestsByIssuerOrderByCreationDateDesc(User issuer);

    List<Request> getRequestOrderByCreationDateDesc();

    void completeRequest(Integer id);
}
