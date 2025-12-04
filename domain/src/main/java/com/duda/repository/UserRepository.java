package com.duda.repository;

import com.duda.entity.User;

public interface UserRepository {

    User save(User user);

    User findByUsername(String username);
}
