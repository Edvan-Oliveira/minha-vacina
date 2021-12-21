package br.com.minhavacina.shared;

public interface Constantes {
    String PADRAO = "http://localhost:8080/minha-vacina-api";
    String MUNICIPIO = PADRAO + "/municipios";
    String CAMPANHA = PADRAO + "/campanhas";
    String CAMPANHA_INATIVA = CAMPANHA + "/inativas";
    String FINALIZA_CAMPANHA = CAMPANHA + "/finalizar";
    String VACINA = PADRAO + "/vacinas";
    String LOGIN = PADRAO + "/usuarios/login";
    String USUARIO = PADRAO + "/usuarios";
    String USUARIO_PELO_TOKEN = USUARIO + "/pelo-token";
    String LOCAL = PADRAO + "/locais";
    String LOCAL_POR_DESCRICAO = LOCAL + FILTROS.POR_DESCRICAO;
    String PERMISSAO_WEB = "ROLE_WEB";

    interface FILTROS {
        String POR_DESCRICAO = "/por-descricao";
    }
}
