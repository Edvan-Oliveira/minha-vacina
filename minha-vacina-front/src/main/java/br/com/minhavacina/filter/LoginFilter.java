package br.com.minhavacina.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static br.com.minhavacina.util.JSFUtil.objetoNaoEstarNuloNemVazio;

@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filtro)
            throws IOException, ServletException {

        HttpServletRequest requisicao = (HttpServletRequest) servletRequest;
        HttpServletResponse resposta = (HttpServletResponse) servletResponse;

        String url = requisicao.getRequestURL().toString();

        if (!url.contains("/login") && !verificarTokenNaSessao(requisicao)) {
            resposta.sendRedirect(requisicao.getServletContext().getContextPath() + "/login.xhtml");
        }

        filtro.doFilter(requisicao, resposta);
    }

    private boolean verificarTokenNaSessao(HttpServletRequest requisicao) {
        HttpSession sessao = requisicao.getSession();
        return objetoNaoEstarNuloNemVazio(sessao) && objetoNaoEstarNuloNemVazio(sessao.getAttribute("token"));
    }
}
