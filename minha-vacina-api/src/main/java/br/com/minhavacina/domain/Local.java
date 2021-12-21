package br.com.minhavacina.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "locais")
public class Local {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descricao;
    private String cep;
    private String bairro;
    private String rua;
    private String numero;
    @JsonIgnoreProperties("campanhas")
    @ManyToOne
    private Municipio municipio;
    @JsonIgnoreProperties("locais")
    @ManyToMany(mappedBy = "locais")
    private List<Campanha> campanhas;
}
