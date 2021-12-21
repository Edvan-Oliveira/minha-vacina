package br.com.minhavacina.request.local;

import br.com.minhavacina.domain.Municipio;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class LocalPostRequest {
    @NotEmpty(message = "A descrição não pode ser vazia")
    @Size(max = 50, message = "A descrição não pode passar de {max} caracteres")
    private String descricao;

    @NotEmpty(message = "O CEP não pode ser vazio")
    @Size(min = 8, max = 9, message = "O CEP não pode passar {max} caracteres")
    private String cep;

    @NotEmpty(message = "O bairro não pode ser vazio")
    @Size(max = 50, message = "O bairro não pode passar de {max} caracteres")
    private String bairro;

    @NotEmpty(message = "A rua não pode ser vazia")
    @Size(max = 50, message = "A rua não pode passar de {max} caracteres")
    private String rua;

    @NotEmpty(message = "O número não pode ser vazio")
    @Size(max = 10, message = "O número não pode passar de {max} caracteres")
    private String numero;

    @NotNull(message = "O município não pode ser nulo")
    private Municipio municipio;
}
