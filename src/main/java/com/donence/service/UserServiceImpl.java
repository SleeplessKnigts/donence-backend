package com.donence.service;

import java.util.Optional;

import com.donence.model.User;
import com.donence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Integer userId) {
        // User user = userRepository.findById(userId)
        //         .orElseThrow(() -> new Exception("User could not be found with given id!"));
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent())
            return user.get();
        else
            return null;
    }
}
