package com.duda.repository;

import com.duda.entity.Pessoa;

import java.util.List;

public interface PessoaRepository {

    Pessoa save(Pessoa pessoa);
    Pessoa findById(String id);
    List<Pessoa> listAll();
    Pessoa update(String id, Pessoa pessoa);
    void delete(String id);

}
