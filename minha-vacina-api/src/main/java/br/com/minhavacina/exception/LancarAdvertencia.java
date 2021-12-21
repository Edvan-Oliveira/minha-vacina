package br.com.minhavacina.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LancarAdvertencia extends RuntimeException {
    public LancarAdvertencia(String mensagem) {
        super(mensagem);
    }
}
