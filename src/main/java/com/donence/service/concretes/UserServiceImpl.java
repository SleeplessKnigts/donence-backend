package com.donence.service.concretes;

import java.util.List;
import java.util.Optional;

import com.donence.model.Request;
import com.donence.model.Role;
import com.donence.model.Roles;
import com.donence.model.User;
import com.donence.repository.RequestRepository;
import com.donence.repository.RoleRepository;
import com.donence.repository.UserRepository;
import com.donence.security.services.UserDetailsImpl;

import com.donence.service.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RequestRepository requestRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Integer userId) {
        // User user = userRepository.findById(userId)
        // .orElseThrow(() -> new Exception("User could not be found with given id!"));
        Optional<User> user = userRepository.findById(userId);
        return user.orElse(null);
    }

    @Override
    public Role findByRole(Roles roleName) {
        Optional<Role> role = roleRepository.findByRole(roleName);
        return role.orElse(null);
    }

    @Override
    public User getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.orElse(null);
    }

    /**
     * @param authentication Spring security authentication object
     * @implNote This method returns a authenticated User object related to
     *           authentication object
     */
    public User getUserByAuthentication(Authentication authentication) {
        if (authentication.getPrincipal() instanceof UserDetailsImpl) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            return userRepository.findByEmail(userDetails.getEmail())
                    .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User cannot find"));
        }
        return null;
    }

    
    @Override
    public List<Request> getRequestsOfUser(User user) {
        return requestRepository.findByIssuerOrderByCreationDateDesc(user);
    }
    
    @Override
    public List<Request> getActiveRequestsOfUser(User user) {
        return requestRepository.findByIsActiveAndIssuerOrderByCreationDateDesc(true, user);
    }
    
    @Override
    public List<Request> getNonActiveRequestOfUser(User user) {
        return requestRepository.findByIsActiveAndIssuerOrderByCreationDateDesc(false,
        user);
    }

    @Override
    public List<Request> getRequestsOfUserFilteredByTypeAndStatus(User user, String type, boolean isActive) {
        return requestRepository.findByIsActiveAndIssuerAndRequestTypeOrderByCreationDateDesc(isActive, user, type);
    }
    
    @Override
    public boolean canUserMakeRequest(User user, String requestType) {
        List<Request> requests = requestRepository.findByIsActiveAndIssuerOrderByCreationDateDesc(true, user);
        for (Request request : requests) {
            if (!request.getRequestType().equals(requestType)) {
                continue;
            }
            return false;
        }
        return true;
    }

    @Override
    public Request createRequest(User user, String requestType) {
        Request request = new Request(requestType, user);
        return requestRepository.save(request);
    }

    @Override
    public Request removeRequest(User user, Integer requestId) {
        Optional<Request> request = requestRepository.findById(requestId);
        if (request.isPresent()) {
            requestRepository.deleteById(requestId);
            return request.get();
        }
        return null;
    }

}
