package br.com.minhavacina.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Vacina {
    private Integer id;
    private String nome;
    private String descricao;
    private String imagem;
    private List<Campanha> campanhas;

    public Vacina() {
        this.campanhas = new ArrayList<>();
    }
}
