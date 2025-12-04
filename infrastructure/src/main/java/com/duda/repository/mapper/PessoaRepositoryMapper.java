package com.duda.repository.mapper;

import com.duda.entity.Pessoa;
import com.duda.repository.orm.PessoaOrm;

public class PessoaRepositoryMapper {

    private PessoaRepositoryMapper() {
    }

    public static Pessoa toEntity(PessoaOrm orm){
        return new Pessoa(
                orm.id(),
                orm.nome(),
                orm.dtNasc(),
                orm.ativo()
        );
    }

    public static PessoaOrm toPersistence(Pessoa pessoaEntity){
        return new PessoaOrm(
                pessoaEntity.getId(),
                pessoaEntity.getNome(),
                pessoaEntity.getDtNasc(),
                pessoaEntity.isAtivo()
        );
    }

}
