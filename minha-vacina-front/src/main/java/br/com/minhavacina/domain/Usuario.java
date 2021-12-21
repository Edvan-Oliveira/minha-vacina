package br.com.minhavacina.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Usuario {
    private Integer id;
    private String nome;
    private String email;
    private String senha;
    private Date dataNascimento;
    private Municipio municipio;
    private List<Vacina> vacinas;
    private List<Permissao> permissoes;
}
