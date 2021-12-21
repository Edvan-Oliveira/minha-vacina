package br.com.minhavacina.request.usuario;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UsuarioLoginRequest {
    @NotEmpty(message = "Email não pode ser vázio")
    @Email(message = "Esse email é inválido")
    private String email;

    @NotEmpty(message = "Senha não pode ser vazia")
    @Size(max = 50, message = "Senha não pode passar de {max} caracteres")
    private String senha;
}
