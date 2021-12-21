package br.com.minhavacina.service;

import br.com.minhavacina.clientrest.ClienteRest;
import br.com.minhavacina.clientrest.TipoAutenticacao;
import br.com.minhavacina.domain.Usuario;
import br.com.minhavacina.dto.LoginDTO;
import br.com.minhavacina.dto.TokenDTO;
import org.springframework.http.ResponseEntity;

import static br.com.minhavacina.shared.Constantes.LOGIN;
import static br.com.minhavacina.shared.Constantes.USUARIO_PELO_TOKEN;

public class LoginService {
    private ClienteRest clienteRest;

    public LoginService() {
        this.clienteRest = new ClienteRest();
    }

    public ResponseEntity<TokenDTO> realizarLogin(LoginDTO loginDTO) {
        return this.clienteRest.chamarMetodoPost(LOGIN, loginDTO, TokenDTO.class);
    }

    public ResponseEntity<Usuario> obterUsuarioPeloToken(TokenDTO tokenDTO) {
        this.clienteRest.setTipoAutenticacao(TipoAutenticacao.BEARER_TOKEN);
        this.clienteRest.setToken(tokenDTO.getToken());
        return this.clienteRest.chamarMetodoPost(USUARIO_PELO_TOKEN, tokenDTO, Usuario.class);
    }
}
