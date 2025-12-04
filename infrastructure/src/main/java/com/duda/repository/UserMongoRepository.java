package com.duda.repository;

import com.duda.repository.orm.UserOrm;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserMongoRepository extends MongoRepository<UserOrm, String> {
    Optional<UserOrm> findByUsername(String username);
}
