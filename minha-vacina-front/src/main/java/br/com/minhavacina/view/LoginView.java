package br.com.minhavacina.view;

import br.com.minhavacina.domain.Permissao;
import br.com.minhavacina.domain.Usuario;
import br.com.minhavacina.dto.LoginDTO;
import br.com.minhavacina.dto.TokenDTO;
import br.com.minhavacina.service.LoginService;
import com.sun.el.parser.Token;
import lombok.Data;
import org.springframework.http.ResponseEntity;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

import static br.com.minhavacina.shared.Constantes.PERMISSAO_WEB;
import static br.com.minhavacina.util.JSFUtil.*;

@Data
@Named
@SessionScoped
public class LoginView implements Serializable {
    private final LoginService loginService;
    private final LoginDTO loginDTO;

    public LoginView() {
        this.loginService = new LoginService();
        this.loginDTO = new LoginDTO();
    }

    public String realizarLogin() {
        ResponseEntity<TokenDTO> tokenDTO = this.loginService.realizarLogin(loginDTO);
        if (!this.validarLogin(tokenDTO)) {
            adicionarMensagemDeErro("Dados incorretos!");
            return null;
        }
        adicionarObjetoNaSessao("token", tokenDTO.getBody().getToken());
        return redirecionarParaPagina("index");
    }

    private boolean validarLogin(ResponseEntity<TokenDTO> tokenDTOResponseEntity) {
        if (!validarRespostaDoToken(tokenDTOResponseEntity)) return false;
        ResponseEntity<Usuario> usuarioResponseEntity = loginService.obterUsuarioPeloToken(tokenDTOResponseEntity.getBody());
        if (!validarRespostaDoUsuario(usuarioResponseEntity)) return false;
        return this.validarUsuarioPermissaoWeb(usuarioResponseEntity.getBody());
    }

    private boolean validarRespostaDoToken(ResponseEntity<TokenDTO> tokenDTOResponseEntity) {
        if (tokenDTOResponseEntity.getStatusCodeValue() != 200
                || objetoEstarNuloOuVazio(tokenDTOResponseEntity.getBody())
                || objetoEstarNuloOuVazio(tokenDTOResponseEntity.getBody().getToken())) {
            return false;
        }
        return true;
    }

    private boolean validarRespostaDoUsuario(ResponseEntity<Usuario> usuarioResponseEntity) {
        if (usuarioResponseEntity.getStatusCodeValue() != 200
                || objetoEstarNuloOuVazio(usuarioResponseEntity.getBody())
                || objetoEstarNuloOuVazio(usuarioResponseEntity.getBody().getId())) {
            return false;
        }
        return true;
    }

    private boolean validarUsuarioPermissaoWeb(Usuario usuario) {
        for (Permissao permissao : usuario.getPermissoes()) {
            if (permissao.getDescricao().equals(PERMISSAO_WEB)) return true;
        }
        return false;
    }
}
