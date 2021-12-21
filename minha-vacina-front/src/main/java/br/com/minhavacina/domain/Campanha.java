package br.com.minhavacina.domain;

import lombok.Data;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Campanha {
    private Integer id;
    private String nome;
    private String descricao;
    private Vacina vacina;
    private Municipio municipio;
    private boolean ativa;
    private Date dataInicio;
    private Date dataFim;
    private Integer idadeMinima;
    private Integer idadeMaxima;
    private LocalTime horarioInicioDia;
    private LocalTime horarioFimDia;
    private List<Local> locais;

    public Campanha() {
        this.vacina = new Vacina();
        this.municipio = new Municipio();
        this.locais = new ArrayList<>();
    }
}
