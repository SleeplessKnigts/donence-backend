package com.donence.service;

import com.donence.model.Role;
import com.donence.model.Roles;
import com.donence.model.User;

public interface UserService {
    User save(User user);
    User getUserById(Integer userId);
    Role findByRole(Roles role);
}
