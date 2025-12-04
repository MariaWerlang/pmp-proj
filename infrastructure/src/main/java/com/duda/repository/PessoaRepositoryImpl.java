package com.duda.repository;

import com.duda.entity.Pessoa;
import com.duda.exception.EntityNotFoundException;
import com.duda.repository.mapper.PessoaRepositoryMapper;
import com.duda.repository.orm.PessoaOrm;
import org.springframework.stereotype.Repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Repository
public class PessoaRepositoryImpl implements PessoaRepository{

    private final PessoaMongoRepository mongoRepository;
    private final Logger logger = LoggerFactory.getLogger(PessoaRepositoryImpl.class);

    public PessoaRepositoryImpl(PessoaMongoRepository mongoRepository){
        this.mongoRepository = mongoRepository;
    }

    @Override
    public Pessoa save(Pessoa pessoa) {
        logger.info("Pessoa Service - Salvando pessoa: {}", pessoa);
        PessoaOrm orm = mongoRepository.save(PessoaRepositoryMapper.toPersistence(pessoa));
        Pessoa p = PessoaRepositoryMapper.toEntity(orm);
        logger.info("Pessoa Service - Pessoa salva: {}", p);
        return p;
    }

    private Pessoa save(String id, Pessoa pessoa) {
        PessoaOrm orm = new PessoaOrm(
            id,
            pessoa.getNome(),
            pessoa.getDtNasc(),
            pessoa.isAtivo()
        );
        return PessoaRepositoryMapper.toEntity(mongoRepository.save(orm));
    }

    @Override
    public Pessoa findById(String id) {
        logger.info("Pessoa Service - Encontrando pessoa com id: {}", id);
        Optional<PessoaOrm> pessoa = mongoRepository.findById(id);
        Optional<Pessoa> pessoaEntity = pessoa.map(PessoaRepositoryMapper::toEntity);
        if (pessoaEntity.isPresent()) {
            Pessoa p = pessoaEntity.get();
            logger.info("Pessoa Service - Pessoa encontrada: {}", p);
            return p;
        }
        else {
            throw new EntityNotFoundException("Pessoa não encontrada ou inativa com o id: " + id);
        }
    }

    @Override
    public List<Pessoa> listAll() {
        logger.info("Pessoa Service - Listando todas as pessoas");
        List<PessoaOrm> pessoasOrm = mongoRepository.findAll();
        return pessoasOrm.stream()
                .map(PessoaRepositoryMapper::toEntity)
                .toList();
    }

    @Override
    public Pessoa update(String id, Pessoa pessoa) {
        logger.info("Pessoa Service - Atualizando pessoa com id: {}", id);
        if(!mongoRepository.existsById(id)){
            throw new EntityNotFoundException("Pessoa não encontrada ou inativa com o id: " + id);
        }
        Pessoa p = save(id, pessoa);
        logger.info("Pessoa Service - Pessoa atualizada: {}", p);
        return p;
    }

    @Override
    public void delete(String id) {
        logger.info("Pessoa Service - Deletando pessoa com id: {}", id);
        if(!mongoRepository.existsById(id)){
            throw new EntityNotFoundException("Pessoa não encontrada ou inativa com o id: " + id);
        }
        Optional<PessoaOrm> p = mongoRepository.findById(id);
        if (p.isEmpty()) {
            throw new EntityNotFoundException("Pessoa não encontrada ou inativa com o id: " + id);
        }
        Pessoa pessoa = PessoaRepositoryMapper.toEntity(p.get());
        pessoa.setAtivo(false);
        save(id, pessoa);
    }
}
