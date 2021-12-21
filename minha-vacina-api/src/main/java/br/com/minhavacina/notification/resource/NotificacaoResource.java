package br.com.minhavacina.notification.resource;

import br.com.minhavacina.notification.model.TokenNotificacao;
import br.com.minhavacina.notification.service.NotificacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static br.com.minhavacina.shared.Constantes.NOTIFICACAO_TOKEN;

@RestController
@RequiredArgsConstructor
@RequestMapping(NOTIFICACAO_TOKEN)
public class NotificacaoResource {

    private final NotificacaoService notificacaoService;

    @PostMapping
    public ResponseEntity<Void> cadastrarOuAtualizarTokenDeNotificacao(@RequestBody @Valid TokenNotificacao tokenNotificacao) {
        notificacaoService.cadastrarOuAtualizarTokenDeNotificacao(tokenNotificacao);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
