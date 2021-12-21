package br.com.minhavacina.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "campanhas")
public class Campanha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @JsonIgnoreProperties("campanhas")
    @ManyToOne
    private Vacina vacina;

    @JsonIgnoreProperties("campanhas")
    @ManyToOne
    private Municipio municipio;
    private boolean ativa;
    private Date dataInicio;
    private Date dataFim;
    private Integer idadeMinima;
    private Integer idadeMaxima;
    private LocalTime horarioInicioDia;
    private LocalTime horarioFimDia;

    @JsonIgnoreProperties("campanhas")
    @ManyToMany
    @JoinTable(name = "campanhas_locais",
            joinColumns = @JoinColumn(name = "campanha_id"),
            inverseJoinColumns = @JoinColumn(name = "local_id"))
    private List<Local> locais;

    @JsonIgnoreProperties({"campanhas", "municipio", "vacinas", "tokenNotificao", "senha", "credentialsNonExpired",
            "accountNonExpired", "authorities", "accountNonLocked", "username", "password", "enabled"})
    @ManyToMany
    @JoinTable(name = "campanhas_usuarios",
            joinColumns = @JoinColumn(name = "campanha_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    private List<Usuario> usuarios;
}
