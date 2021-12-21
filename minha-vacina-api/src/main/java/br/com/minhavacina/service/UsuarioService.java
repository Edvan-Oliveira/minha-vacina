package br.com.minhavacina.service;

import br.com.minhavacina.domain.Permissao;
import br.com.minhavacina.domain.Usuario;
import br.com.minhavacina.exception.LancarAdvertencia;
import br.com.minhavacina.mapper.UsuarioMapper;
import br.com.minhavacina.repository.UsuarioRepository;
import br.com.minhavacina.request.usuario.UsuarioGetRequest;
import br.com.minhavacina.request.usuario.UsuarioPostRequest;
import br.com.minhavacina.request.usuario.UsuarioPutRequest;
import br.com.minhavacina.response.TokenLoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.com.minhavacina.util.Utilitaria.criptografarSenha;
import static br.com.minhavacina.util.Utilitaria.objetoEstarNuloOuVazio;

@RequiredArgsConstructor
@Service
public class UsuarioService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final TokenService tokenService;

    @Override
    public Usuario loadUserByUsername(String email) throws UsernameNotFoundException {
        return Optional.ofNullable(usuarioRepository.findByEmail(email))
                .orElseThrow(() -> new UsernameNotFoundException("Usuário [ " + email + " ] não encontrado"));
    }

    public List<UsuarioGetRequest> listarTodosOsUsuarios() {
        return usuarioMapper.converterParaListaUsuarioGetRequest(usuarioRepository.findAll());
    }

    public UsuarioGetRequest buscarUsuarioGetRequestPorId(Integer id) {
        return usuarioMapper.converterParaUsuarioGetRequest(usuarioRepository.findById(id)
                .orElseThrow(() -> new LancarAdvertencia("Usuário não encntrado")));
    }

    public Usuario buscarUsuarioPorId(Integer id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new LancarAdvertencia("Usuário não encontrado"));
    }

    public UsuarioGetRequest cadastrarNovoUsuario(UsuarioPostRequest usuarioPostRequest) {
        Usuario usuario = usuarioMapper.converterParaUsuario(usuarioPostRequest);
        criptografarSenha(usuario);
        definirPermissaoDeApp(usuario);
        return usuarioMapper.converterParaUsuarioGetRequest(usuarioRepository.save(usuario));
    }

    public void atualizarUsuario(UsuarioPutRequest usuarioPutRequest) {
        Usuario usuarioSalvo = buscarUsuarioPorId(usuarioPutRequest.getId());
        verificarSenhaAntesDeAtualizar(usuarioPutRequest, usuarioSalvo);
        Usuario usuarioNovo = usuarioMapper.converterParaUsuario(usuarioPutRequest);
        salvarCamposImutaveis(usuarioNovo, usuarioSalvo);
        usuarioRepository.save(usuarioNovo);
    }

    public void deletarUsuario(Integer id) {
        Usuario usuario = buscarUsuarioPorId(id);
        usuarioRepository.delete(usuario);
    }

    public UsuarioGetRequest obterUsuarioPeloToken(TokenLoginResponse tokenLoginResponse) {
        Integer idUsuario = tokenService.obterIdDoUsuario(tokenLoginResponse.getToken());
        Usuario usuario = buscarUsuarioPorId(idUsuario);
        return usuarioMapper.converterParaUsuarioGetRequest(usuario);
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    private void verificarSenhaAntesDeAtualizar(UsuarioPutRequest usuarioPutRequest, Usuario usuarioSalvo) {
        String senha = objetoEstarNuloOuVazio(usuarioPutRequest.getSenha())
                ? usuarioSalvo.getSenha() : criptografarSenha(usuarioPutRequest.getSenha());
        usuarioPutRequest.setSenha(senha);
    }

    private void salvarCamposImutaveis(Usuario usuarioNovo, Usuario usuarioSalvo) {
        usuarioNovo.setTokenNotificao(usuarioSalvo.getTokenNotificao());
    }

    private void definirPermissaoDeApp(Usuario usuario) {
        usuario.setPermissoes(new ArrayList<>());
        Permissao permissao = new Permissao();
        permissao.setId(1);
        permissao.setDescricao("ROLE_APP");
        usuario.getPermissoes().add(permissao);
    }
}
