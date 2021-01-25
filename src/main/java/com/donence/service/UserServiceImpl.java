package com.donence.service;

import java.util.Optional;

import com.donence.model.Role;
import com.donence.model.Roles;
import com.donence.model.User;
import com.donence.repository.RoleRepository;
import com.donence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

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

}
