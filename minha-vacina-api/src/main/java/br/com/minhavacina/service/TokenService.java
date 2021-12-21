package br.com.minhavacina.service;

import br.com.minhavacina.domain.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${jwt.senha}")
    private String senha;

    @Value("${jwt.expiracao}")
    private String expiracao;

    public String gerarToken(Authentication authentication) {

        Date dataAtual = new Date();
        Date dataExpiracao = new Date(dataAtual.getTime() + Integer.parseInt(expiracao));

        Usuario usuario = (Usuario) authentication.getPrincipal();

        return Jwts.builder()
                .setIssuer("Minha Vacina API")
                .setId(usuario.getId().toString())
                .setSubject(usuario.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256, senha)
                .compact();
    }

    public boolean validarToken(String token) {
        try {
            Jwts.parser().setSigningKey(senha).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Integer obterIdDoUsuario(String token) {
        Claims dados = Jwts.parser().setSigningKey(senha).parseClaimsJws(token).getBody();
        return Integer.parseInt(dados.getId());
    }
}
