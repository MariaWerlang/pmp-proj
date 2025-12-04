package com.duda.repository;

import com.duda.entity.User;
import com.duda.exception.NotFoundException;
import com.duda.repository.mapper.UserRepositoryMapper;
import com.duda.repository.orm.UserOrm;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository{

    private final UserMongoRepository mongoRepository;
    private final PasswordEncoder passwordEncoder;

    public UserRepositoryImpl(UserMongoRepository mongoRepository, PasswordEncoder passwordEncoder) {
        this.mongoRepository = mongoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserOrm orm = mongoRepository.save(UserRepositoryMapper.toPersistence(user));
        return UserRepositoryMapper.toEntity(orm);
    }

    @Override
    public User findByUsername(String username) {
        Optional<UserOrm> opt = mongoRepository.findByUsername(username);
        if(opt.isEmpty()) {
            throw new NotFoundException("Usuário não encontrado com o username: " + username);
        }
        return UserRepositoryMapper.toEntity(opt.get());
    }

}
