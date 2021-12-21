package br.com.minhavacina.request.local;

import br.com.minhavacina.domain.Municipio;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class LocalFiltroDescricaoPostRequest {
    @NotEmpty(message = "A descrição não pode ser vazia")
    @Size(max = 50, message = "A descrição não pode passar de {max} caracteres")
    private String descricao;

    @NotNull(message = "O município não pode ser nulo")
    private Municipio municipio;
}
