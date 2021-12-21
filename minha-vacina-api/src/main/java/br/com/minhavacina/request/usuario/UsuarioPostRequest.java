package br.com.minhavacina.request.usuario;

import br.com.minhavacina.domain.Municipio;
import br.com.minhavacina.domain.Vacina;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
public class UsuarioPostRequest {
    @NotEmpty(message = "Nome não pode ser vazio")
    @Size(max = 50, message = "Nome do usuário não pode passar de {max} caracteres")
    private String nome;

    @NotNull(message = "Data de nascimento do usuário não pode ser nula")
    private Date dataNascimento;

    @NotNull(message = "Município não pode ser nulo")
    private Municipio municipio;

    @NotEmpty(message = "Email não pode ser vázio")
    @Email(message = "Esse email é inválido")
    private String email;

    @NotEmpty(message = "Senha não pode ser vazia")
    @Size(max = 50, message = "Senha não pode passar de {max} caracteres")
    private String senha;

    private List<Vacina> vacinas;
}
