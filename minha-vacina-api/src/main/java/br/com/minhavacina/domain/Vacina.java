package br.com.minhavacina.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "vacinas")
public class Vacina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(columnDefinition = "TEXT")
    private String imagem;

    @JsonIgnoreProperties({"vacina", "usuarios"})
    @OneToMany(mappedBy = "vacina")
    private List<Campanha> campanhas;

    @JsonIgnoreProperties({"vacinas", "campanhas", "tokenNotificao", "senha", "credentialsNonExpired",
            "accountNonExpired", "authorities", "accountNonLocked", "username", "password", "enabled"})
    @ManyToMany(mappedBy = "vacinas", cascade = CascadeType.DETACH)
    private List<Usuario> usuarios;
}
