package com.duda.config.service;

import com.duda.repository.PessoaRepository;
import com.duda.service.PessoaService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PessoaServiceConfig {
    @Bean
    public PessoaService pessoaService(PessoaRepository repository){
        return new PessoaService(repository);
    }
}
