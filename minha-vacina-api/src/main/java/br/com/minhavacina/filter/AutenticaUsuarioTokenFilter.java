package br.com.minhavacina.filter;

import br.com.minhavacina.domain.Usuario;
import br.com.minhavacina.repository.UsuarioRepository;
import br.com.minhavacina.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
public class AutenticaUsuarioTokenFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest requisicao, HttpServletResponse resposta, FilterChain filtro)
            throws ServletException, IOException {

        String token = obterTokenDoCabecalhoDaRequisicao(requisicao);
        boolean tokenValido = tokenService.validarToken(token);

        if (tokenValido) {
            autenticarUsuario(token);
        }

        filtro.doFilter(requisicao, resposta);
    }

    private String obterTokenDoCabecalhoDaRequisicao(HttpServletRequest requisicao) {
        String token = requisicao.getHeader("Authorization");

        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.substring(7);
    }

    private void autenticarUsuario(String token) {
        Integer id = tokenService.obterIdDoUsuario(token);
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(usuario, null, usuario.getPermissoes());

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
    }
}
