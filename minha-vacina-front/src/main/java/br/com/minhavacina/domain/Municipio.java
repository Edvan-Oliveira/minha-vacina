package br.com.minhavacina.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Municipio {
    @EqualsAndHashCode.Include
    private Integer id;
    private String nome;
    private List<Campanha> campanhas;

    public Municipio() {
        this.campanhas = new ArrayList<>();
    }
}
