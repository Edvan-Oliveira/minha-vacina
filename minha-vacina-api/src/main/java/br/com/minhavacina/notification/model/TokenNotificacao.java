package br.com.minhavacina.notification.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenNotificacao {
    @NotEmpty(message = "Token de notificação é obrigatório")
    private String token;
}
