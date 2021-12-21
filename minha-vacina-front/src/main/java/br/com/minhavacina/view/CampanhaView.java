package br.com.minhavacina.view;

import br.com.minhavacina.domain.Campanha;
import br.com.minhavacina.domain.Local;
import br.com.minhavacina.domain.Municipio;
import br.com.minhavacina.domain.Vacina;
import br.com.minhavacina.dto.HorariosCampanhaDTO;
import br.com.minhavacina.service.CampanhaService;
import br.com.minhavacina.util.JSFUtil;
import lombok.Data;
import org.springframework.http.ResponseEntity;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static br.com.minhavacina.util.JSFUtil.*;

@Data
@Named
@ViewScoped
public class CampanhaView implements Serializable {
    private CampanhaService campanhaService;
    private List<Municipio> listaDeMunicipios;
    private List<Vacina> listaDeVacinas;
    private Campanha campanha;
    private List<Campanha> listaDeCampanhas;
    private List<Campanha> filtroDaListaDeCampanhas;
    private boolean statusListaCampanhas;
    private HorariosCampanhaDTO horariosCampanhaDTO;
    private Local localEscolhido;

    public CampanhaView() {
        this.campanhaService = new CampanhaService();
        this.campanha = new Campanha();
        this.listaDeCampanhas = new ArrayList<>();
        this.filtroDaListaDeCampanhas = new ArrayList<>();
        this.statusListaCampanhas = true;
        this.horariosCampanhaDTO = new HorariosCampanhaDTO();
        this.localEscolhido = new Local();
    }

    public void carregarDadosdaTela() {
        this.listaDeMunicipios = this.campanhaService.listarTodosOsMunipios().getBody();
        this.listaDeVacinas = this.campanhaService.listarTodasAsVacinas().getBody();
        this.listaDeCampanhas = this.campanhaService.listarCampanhasAtivas().getBody();
    }

    public void cadastrarCampanha() {
        if (!this.validarHorariosCampanha()) return;
        this.horariosCampanhaDTO.popularHorarios(this.campanha);
        boolean cadastrou = validarResponseEntity(this.campanhaService.cadastrarCampanha(this.campanha));
        if (cadastrou) adicionarMensagemDeSucesso("Campanha cadastrada com sucesso!");
        else adicionarMensagemDeErro();
        this.limparListaCampanhas();
        fecharDialogo("dlgCadastroCampanha");
    }

    public void abrirDialogoDetalheCampanha() {
        this.popularCampanhaPorId();
        abrirDialogo("dlgDetalheCampanha");
    }

    public void abrirDialogoAtualizarCampanha() {
        this.popularCampanhaPorId();
        abrirDialogo("dlgAtualizaCampanha");
    }

    public void atualizarCampanha() {
        if (!this.validarHorariosCampanha()) return;
        this.horariosCampanhaDTO.popularHorarios(this.campanha);
        boolean atualizou = validarResponseEntityAtualizacao(this.campanhaService.atualizarCampanha(this.campanha));
        if (atualizou) adicionarMensagemDeSucesso("Campanha atualizada dom sucesso!");
        else adicionarMensagemDeErro();
        this.limparListaCampanhas();
        fecharDialogo("dlgAtualizaCampanha");
    }

    public boolean validarHorariosCampanha() {
        if (this.horariosCampanhaDTO.validarHorarios()) return true;
        return adicionarMensagemDeErro("Hora campanha inválida!");
    }

    public void limparListaCampanhas() {
        this.campanha = new Campanha();
        this.horariosCampanhaDTO = new HorariosCampanhaDTO();
        this.eventoAjaxListarCampanhasAtivasOuInativas();
    }

    public String formatarDataParaVisualizacao(String data) {
        String[] s = data.split("T");
        String dataFormatada = "----";
        try {
            if (objetoNaoEstarNuloNemVazio(data)) {
                Date dataUtil = new SimpleDateFormat("yyyy-MM-dd").parse(s[0]);
                dataFormatada = new SimpleDateFormat("dd/MM/yyyy").format(dataUtil);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dataFormatada;
    }

    public String formatarIdadeParaVisualizacao(String idade) {
        return objetoNaoEstarNuloNemVazio(idade) ? idade + " anos" : "----";
    }

    public void eventoAjaxListarCampanhasAtivasOuInativas() {
        ResponseEntity<List<Campanha>> campanhasResponseEntity;

        if (statusListaCampanhas) campanhasResponseEntity = this.campanhaService.listarCampanhasAtivas();
        else campanhasResponseEntity = this.campanhaService.listarCampanhasInativas();

        this.listaDeCampanhas = validarResponseEntity(campanhasResponseEntity)
                ? campanhasResponseEntity.getBody() : new ArrayList<>();
    }

    private void popularCampanhaPorId() {
        ResponseEntity<Campanha> campanhaResponseEntity = this.campanhaService
                .buscarCampanhaPorId(this.campanha.getId());
        if (validarResponseEntity(campanhaResponseEntity)) {
            this.campanha = campanhaResponseEntity.getBody();
            this.horariosCampanhaDTO.popularHorariosTextos(this.campanha);
        } else {
            this.campanha = new Campanha();
        }
    }

    public void finalizarCampanha() {
        boolean atualizou = validarResponseEntityAtualizacao(this.campanhaService.finalizarCampanha(campanha.getId()));
        if (atualizou) adicionarMensagemDeSucesso("Campanha Finalizada!");
        else adicionarMensagemDeErro();
        this.limparListaCampanhas();
        this.statusListaCampanhas = false;
    }

    public List<Local> buscarLocaisPorDescricao(String descricao) {
        Local local = new Local();
        local.setDescricao(descricao);
        local.setMunicipio(campanha.getMunicipio());
        List<Local> body = this.campanhaService.listarLocaisPorDescricaoEMunicipio(local).getBody();
        return body;
    }

    public void adicionarLocalNaCampanha() {
        if (!validarFormularioAdicionaLocalNaCampanha()) return;
        this.campanha.getLocais().add(this.localEscolhido);
        this.localEscolhido = new Local();
    }

    private boolean validarFormularioAdicionaLocalNaCampanha() {
        if (this.campanha.getLocais().contains(this.localEscolhido))
            return adicionarMensagemDeAviso("O endereço já está na lista");
        if (objetoEstarNuloOuVazio(this.localEscolhido))
            return adicionarMensagemDeAviso("Escolha um posto para adicionar");
        return true;
    }

    public void removerLocalDaCampanha() {
        this.campanha.getLocais().remove(this.localEscolhido);
        this.localEscolhido = new Local();
    }

    public void abrirDialogoNovoLocal() {
        this.localEscolhido = new Local();
        abrirDialogo("dlgNovoLocal");
    }

    public void cadastrarNovoLocal() {
        this.localEscolhido.setMunicipio(this.campanha.getMunicipio());
        ResponseEntity<Local> localSalvo = this.campanhaService.cadastrarNovoLocal(this.localEscolhido);
        if (validarResponseEntity(localSalvo)) {
            adicionarMensagemDeSucesso("Local cadastrado");
            this.localEscolhido = localSalvo.getBody();
            this.adicionarLocalNaCampanha();
        } else adicionarMensagemDeErro();
        this.localEscolhido = new Local();
        fecharDialogo("dlgNovoLocal");
    }

    public String sair() {
        removerObjetoDaSessao("token");
        return redirecionarParaPagina("login");
    }
}
