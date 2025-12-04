package com.duda.repository;

import com.duda.repository.orm.PessoaOrm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PessoaMongoRepository extends MongoRepository<PessoaOrm, String> {

    @Override
    @Query("{ 'ativo' : true }")
    List<PessoaOrm> findAll();

    @Override
    @Query("{ '_id' : ?0, 'ativo' : true }")
    Optional<PessoaOrm> findById(String id);

    @Override
    @Query("{ 'ativo' : true }")
    Page<PessoaOrm> findAll(Pageable pageable);

}
