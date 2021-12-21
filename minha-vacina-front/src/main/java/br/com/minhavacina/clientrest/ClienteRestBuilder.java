package br.com.minhavacina.clientrest;

public class ClienteRestBuilder {

    private final ClienteRest cliente;

    public ClienteRestBuilder() {
        this.cliente = new ClienteRest();
    }

    public static br.com.minhavacina.clientrest.ClienteRestBuilder clienteRestComAutenticaoJWT(String token) {
        return new br.com.minhavacina.clientrest.ClienteRestBuilder()
                .tipoAutenticacao(TipoAutenticacao.BEARER_TOKEN)
                .token(token);
    }

    public static br.com.minhavacina.clientrest.ClienteRestBuilder clienteRestComAutenticaoBasica(String usuario, String senha) {
        return new br.com.minhavacina.clientrest.ClienteRestBuilder()
                .tipoAutenticacao(TipoAutenticacao.BASIC_AUTH)
                .usuario(usuario)
                .senha(senha);
    }

    public br.com.minhavacina.clientrest.ClienteRestBuilder tipoAutenticacao(TipoAutenticacao tipoAutenticacao) {
        this.cliente.setTipoAutenticacao(tipoAutenticacao);
        return this;
    }

    public br.com.minhavacina.clientrest.ClienteRestBuilder token(String token) {
        this.cliente.setToken(token);
        return this;
    }

    public br.com.minhavacina.clientrest.ClienteRestBuilder usuario(String usuario) {
        this.cliente.setUsuario(usuario);
        return this;
    }

    public br.com.minhavacina.clientrest.ClienteRestBuilder senha(String senha) {
        this.cliente.setSenha(senha);
        return this;
    }

    public ClienteRest construir() {
        return this.cliente;
    }
}
