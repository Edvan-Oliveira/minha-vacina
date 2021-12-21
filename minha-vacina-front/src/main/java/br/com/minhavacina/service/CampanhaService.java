package br.com.minhavacina.service;

import br.com.minhavacina.clientrest.ClienteRest;
import br.com.minhavacina.clientrest.TipoAutenticacao;
import br.com.minhavacina.domain.Campanha;
import br.com.minhavacina.domain.Local;
import br.com.minhavacina.domain.Municipio;
import br.com.minhavacina.domain.Vacina;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static br.com.minhavacina.shared.Constantes.*;
import static br.com.minhavacina.util.JSFUtil.resgatarObjetoDaSessao;

public class CampanhaService {
    private ClienteRest clienteRest;

    public CampanhaService() {
        this.clienteRest = new ClienteRest();
        this.recuperarTokenDaSessao();
    }

    private void recuperarTokenDaSessao() {
        String token = (String) resgatarObjetoDaSessao("token");
        this.clienteRest.setTipoAutenticacao(TipoAutenticacao.BEARER_TOKEN);
        this.clienteRest.setToken(token);
    }

    public ResponseEntity<List<Municipio>> listarTodosOsMunipios() {
        return this.clienteRest.chamarMetodoGetListagem(MUNICIPIO);
    }

    public ResponseEntity<List<Vacina>> listarTodasAsVacinas() {
        return this.clienteRest.chamarMetodoGetListagem(VACINA);
    }

    public ResponseEntity<Campanha> cadastrarCampanha(Campanha campanha) {
        return this.clienteRest.chamarMetodoPost(CAMPANHA, campanha, Campanha.class);
    }

    public ResponseEntity<List<Campanha>> listarCampanhasAtivas() {
        return this.clienteRest.chamarMetodoGetListagem(CAMPANHA);
    }

    public ResponseEntity<List<Campanha>> listarCampanhasInativas() {
        return this.clienteRest.chamarMetodoGetListagem(CAMPANHA_INATIVA, Campanha[].class);
    }

    public ResponseEntity<Void> atualizarCampanha(Campanha campanha) {
        return this.clienteRest.chamarMetodoPut(CAMPANHA, campanha);
    }

    public ResponseEntity<Campanha> buscarCampanhaPorId(Integer id) {
        return clienteRest.chamarMetodoGet(CAMPANHA + "/" + id, Campanha.class);
    }

    public ResponseEntity<Void> finalizarCampanha(Integer id) {
        return clienteRest.chamarMetodoPut(FINALIZA_CAMPANHA + "/" + id, Campanha.class);
    }

    public ResponseEntity<Local> buscarLocalPorId(Integer id) {
        return this.clienteRest.chamarMetodoGet(LOCAL + "/" + id, Local.class);
    }

    public ResponseEntity<List<Local>> listarLocaisPorDescricaoEMunicipio(Local local) {
        return this.clienteRest.chamarMetodoPostListagem(LOCAL_POR_DESCRICAO, local);
    }

    public ResponseEntity<Local> cadastrarNovoLocal(Local local) {
        return this.clienteRest.chamarMetodoPost(LOCAL, local, Local.class);
    }
}
