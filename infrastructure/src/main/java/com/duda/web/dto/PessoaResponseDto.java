package com.duda.web.dto;

public class PessoaResponseDto {

    private String id;
    private String nome;
    private String dtNasc;

    // Construtores
    public PessoaResponseDto() {}

    public PessoaResponseDto(String id, String nome, String dtNasc) {
        this.id = id;
        this.nome = nome;
        this.dtNasc = dtNasc;
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(String dtNasc) {
        this.dtNasc = dtNasc;
    }



}
