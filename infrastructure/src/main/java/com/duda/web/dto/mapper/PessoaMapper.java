package com.duda.web.dto.mapper;

import com.duda.entity.Pessoa;
import com.duda.web.dto.PessoaCreateDto;
import com.duda.web.dto.PessoaResponseDto;
import com.duda.web.dto.PessoaUpdateDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PessoaMapper {

    private static String datePattern = "dd/MM/yyyy";

    private PessoaMapper() {
    }

    public static Pessoa toEntity(PessoaCreateDto createDto){
        LocalDateTime date = LocalDate.parse(createDto.getDtNasc(), DateTimeFormatter.ofPattern(datePattern))
                .atStartOfDay();
        Pessoa pessoa = new Pessoa(
                null,
                createDto.getNome(),
                date,
                true
        );
        pessoa.setDtNasc(date);
        return pessoa;
    }

    public static Pessoa toEntity(PessoaUpdateDto updateDto){
        LocalDateTime date = null;
        if (updateDto.getDtNasc() != null) {
            date = LocalDate.parse(updateDto.getDtNasc(), DateTimeFormatter.ofPattern(datePattern))
                    .atStartOfDay();
        }
        Pessoa pessoa = new Pessoa(
                null,
                updateDto.getNome(),
                date,
                true
        );
        pessoa.setDtNasc(date);
        return pessoa;
    }

    public static PessoaResponseDto toResponse(Pessoa pessoa){
        String date = pessoa.getDtNasc().toLocalDate().format(DateTimeFormatter.ofPattern(datePattern));
        return new PessoaResponseDto(
                pessoa.getId(),
                pessoa.getNome(),
                date
        );
    }

}
