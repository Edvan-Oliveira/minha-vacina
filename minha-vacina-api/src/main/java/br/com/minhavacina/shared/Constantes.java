package br.com.minhavacina.shared;

public interface Constantes {
    String PADRAO = "/minha-vacina-api";
    String WEB = PADRAO + "/web";
    String APP = PADRAO + "/app";
    String MUNICIPIO = PADRAO + "/municipios";
    String USUARIO = PADRAO + "/usuarios";
    String CAMPANHA = PADRAO + "/campanhas";
    String CAMPANHA_INATIVA = "/inativas";
    String FINALIZA_CAMPANHA = "/finalizar/{id}";
    String ASSOCIAR_USUARIO = "/associar-usuario/{id}";
    String DESASSOCIAR_USUARIO = "/desassociar-usuario/{id}";
    String VACINA = PADRAO + "/vacinas";
    String LOGIN = "/login";
    String LOGIN_ABSOLUTO =  USUARIO + "/login";
    String VALIDA_EMAIL = "/validar-email/{email}";
    String VALIDA_EMAIL_ABSOLUTO = USUARIO + "/validar-email/{email}";
    String LOCAL = PADRAO + "/locais";
    String NOTIFICACAO = PADRAO + "/notificacao";
    String NOTIFICACAO_TOKEN =  NOTIFICACAO + "/token";
    String EXPO_NOTIFICACAO = "https://exp.host/--/api/v2/push/send";

    interface FILTROS {
        String POR_DESCRICAO = "/por-descricao";
    }

    interface CONSULTAS {
        String LISTA = "/listar";
        String PELO_TOKEN = "/pelo-token";
    }

    interface ROLES {
        String PREFIXO = "ROLE_";
        String WEB = PREFIXO + "WEB";
        String APP = PREFIXO + "APP";
    }

    interface ROTAS_PUBLICAS {
        String[] METODO_POST = { LOGIN_ABSOLUTO, USUARIO };
        String[] METODO_GET = { MUNICIPIO, VALIDA_EMAIL_ABSOLUTO };
    }
}
