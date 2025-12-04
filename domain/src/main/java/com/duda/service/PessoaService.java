package com.duda.service;

import com.duda.entity.Pessoa;
import com.duda.repository.PessoaRepository;

import java.util.List;

public class PessoaService {
    private final PessoaRepository repository;


    public PessoaService(PessoaRepository repository){
        this.repository = repository;
    }

    public Pessoa save(Pessoa pessoa){
        return repository.save(pessoa);
    }

    public Pessoa findById(String id){
        return repository.findById(id);
    }

    public List<Pessoa> listAll(){
        return repository.listAll();
    }

    public Pessoa update(String id, Pessoa pessoa){
        Pessoa existente = repository.findById(id);
        if (pessoa.getDtNasc() != null) {
            existente.setDtNasc(pessoa.getDtNasc());
        }
        if (pessoa.getNome() != null) {
            existente.setNome(pessoa.getNome());
        }
        return repository.update(id, existente);
    }

    public void delete(String id){
        repository.delete(id);
    }

}
