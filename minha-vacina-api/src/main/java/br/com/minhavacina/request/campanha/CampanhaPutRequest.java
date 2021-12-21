package br.com.minhavacina.request.campanha;

import br.com.minhavacina.domain.Local;
import br.com.minhavacina.domain.Municipio;
import br.com.minhavacina.domain.Vacina;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Data
public class CampanhaPutRequest {
    @NotNull(message = "Id da campanha não pode ser nulo")
    @Min(value = 1, message = "Id da campanha não pode ser menor que {value}")
    private Integer id;

    @NotEmpty(message = "Nome da campanha não pode ser vazio")
    @Size(max = 200, message = "Nome da campanha não pode passar de {max} caracteres")
    private String nome;

    @NotEmpty(message = "Descrição da campanha não pode ser vazio")
    @Size(max = 4000, message = "Descrição da campanha não pode passar de {max) caracteres")
    private String descricao;

    @NotNull(message = "Vacina da campanha não pode ser nula")
    private Vacina vacina;

    @NotNull(message = "Município da campanha não pode ser nulo")
    private Municipio municipio;

    private boolean ativa;
    private Date dataInicio;
    private Date dataFim;

    @Min(value = 0, message = "Idade mínima da campanha não pode ser menor que {value}")
    private Integer idadeMinima;

    @Min(value = 0, message = "Idade máxima da campanha não pode ser menor que {value}")
    private Integer idadeMaxima;

    @NotNull(message = "A hora de início do dia não pode ser nula")
    private LocalTime horarioInicioDia;

    @NotNull(message = "A hora de termino do dia não pode ser nula")
    private LocalTime horarioFimDia;

    @NotEmpty(message = "A campanha precisa ter pelo menos um local")
    private List<Local> locais;
}
