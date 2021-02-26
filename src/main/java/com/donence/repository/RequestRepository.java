package com.donence.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.donence.model.Request;
import com.donence.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {
    List<Request> findByIsActiveOrderByCreationDateDesc(boolean isActive);

    List<Request> findByIsActiveAndRequestTypeOrderByCreationDateDesc(boolean isActive, String requestType);

    List<Request> findByIsActiveAndCreationDateBetween(boolean isActive, LocalDateTime creationDateStart,
            LocalDateTime creationDateEnd);

    List<Request> findByIsActiveAndRequestTypeAndCreationDateBetween(boolean isActive, String requestType,
            LocalDateTime creationDateStart, LocalDateTime creationDateEnd);

    List<Request> findByIssuerOrderByCreationDateDesc(User issuer);

    List<Request> findByIsActiveAndIssuerOrderByCreationDateDesc(boolean isActive, User issuer);

    List<Request> findByIsActiveAndIssuerAndRequestTypeOrderByCreationDateDesc(boolean isActive, User issuer,
            String requestType);

}
