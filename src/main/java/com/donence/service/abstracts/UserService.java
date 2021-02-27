package com.donence.service.abstracts;

import java.util.List;

import com.donence.model.Request;
import com.donence.model.Role;
import com.donence.model.Roles;
import com.donence.model.User;

import org.springframework.security.core.Authentication;

public interface UserService {
    User save(User user);

    User getUserById(Integer userId);

    Role findByRole(Roles role);

    User getUserByEmail(String email);

    User getUserByAuthentication(Authentication authentication);

    List<Request> getRequestsOfUser(User user);

    List<Request> getActiveRequestsOfUser(User user);

    List<Request> getNonActiveRequestOfUser(User user);

    boolean canUserMakeRequest(User user, String requestType);

    Request createRequest(User user, String requestType);

    Request removeRequest(User user, Integer requestId);
}
