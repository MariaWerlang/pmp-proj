package com.duda.repository.orm;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(value = "pessoas")
public record PessoaOrm(
        @Id
        String id,
        String nome,
        LocalDateTime dtNasc,
        boolean ativo
) {
}
