package br.com.minhavacina.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailValidaResponse {
    private String email;
    private boolean valido;
}
