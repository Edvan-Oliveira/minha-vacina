package br.com.minhavacina.resource;

import br.com.minhavacina.request.usuario.UsuarioGetRequest;
import br.com.minhavacina.request.usuario.UsuarioLoginRequest;
import br.com.minhavacina.request.usuario.UsuarioPostRequest;
import br.com.minhavacina.request.usuario.UsuarioPutRequest;
import br.com.minhavacina.response.EmailValidaResponse;
import br.com.minhavacina.response.TokenLoginResponse;
import br.com.minhavacina.service.TokenService;
import br.com.minhavacina.service.UsuarioService;
import br.com.minhavacina.shared.Constantes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

import static br.com.minhavacina.util.Utilitaria.objetoNaoEstarNuloNemVazio;

@RequiredArgsConstructor
@RestController
@RequestMapping(Constantes.USUARIO)
public class UsuarioResource {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping(Constantes.CONSULTAS.PELO_TOKEN)
    public ResponseEntity<UsuarioGetRequest> obterUsuarioPeloToken(@RequestBody TokenLoginResponse tokenLoginResponse) {
        return ResponseEntity.ok(usuarioService.obterUsuarioPeloToken(tokenLoginResponse));
    }

    @GetMapping(Constantes.CONSULTAS.LISTA)
    public ResponseEntity<List<UsuarioGetRequest>> listarTodosOsUsuarios() {
        return ResponseEntity.ok(usuarioService.listarTodosOsUsuarios());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UsuarioGetRequest> buscarUsuarioPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioGetRequestPorId(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioGetRequest> cadastrarNovoUsuario(@RequestBody @Valid UsuarioPostRequest usuarioPostRequest) {
        return new ResponseEntity<>(usuarioService.cadastrarNovoUsuario(usuarioPostRequest), HttpStatus.CREATED);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Void> atualizarUsuario(@RequestBody @Valid UsuarioPutRequest usuarioPutRequest) {
        usuarioService.atualizarUsuario(usuarioPutRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleletarUsuario(@PathVariable Integer id) {
        usuarioService.deletarUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(Constantes.LOGIN)
    public ResponseEntity<TokenLoginResponse> realizarLogin(@RequestBody @Valid UsuarioLoginRequest usuarioLoginRequest) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(usuarioLoginRequest.getEmail(), usuarioLoginRequest.getSenha());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        String token = tokenService.gerarToken(authentication);

        return ResponseEntity.ok(TokenLoginResponse.builder().tipo("Bearer").token(token).build());
    }

    @GetMapping(path = Constantes.VALIDA_EMAIL)
    public ResponseEntity<EmailValidaResponse> validarEmail(@PathVariable String email) {
        boolean emailValido = objetoNaoEstarNuloNemVazio(usuarioService.buscarUsuarioPorEmail(email));
        return ResponseEntity.ok(EmailValidaResponse.builder().email(email).valido(emailValido).build());
    }
}
