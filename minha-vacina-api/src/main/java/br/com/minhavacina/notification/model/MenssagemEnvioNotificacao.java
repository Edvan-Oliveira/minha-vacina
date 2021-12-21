package br.com.minhavacina.notification.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MenssagemEnvioNotificacao {
    private String to;
    private String sound;
    private String title;
    private String body;
    private DadosEnvioNotificao data;
}
