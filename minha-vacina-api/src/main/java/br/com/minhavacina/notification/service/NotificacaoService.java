package br.com.minhavacina.notification.service;

import br.com.minhavacina.domain.Campanha;
import br.com.minhavacina.domain.Usuario;
import br.com.minhavacina.domain.Vacina;
import br.com.minhavacina.notification.model.DadosEnvioNotificao;
import br.com.minhavacina.notification.model.MenssagemEnvioNotificacao;
import br.com.minhavacina.notification.model.RespostaEnvioNotificacao;
import br.com.minhavacina.notification.model.TokenNotificacao;
import br.com.minhavacina.repository.MunicipioRepository;
import br.com.minhavacina.repository.UsuarioRepository;
import br.com.minhavacina.repository.VacinaRepository;
import br.com.minhavacina.service.VacinaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static br.com.minhavacina.shared.Constantes.EXPO_NOTIFICACAO;
import static br.com.minhavacina.util.Utilitaria.obterUsuarioAutenticado;

@Service
@RequiredArgsConstructor
public class NotificacaoService {

    private final UsuarioRepository usuarioRepository;
    private final VacinaRepository vacinaRepository;
    private final MunicipioRepository municipioRepository;


    public void cadastrarOuAtualizarTokenDeNotificacao(TokenNotificacao tokenNotificacao) {
        Usuario usuario = obterUsuarioAutenticado();
        usuario.setTokenNotificao(tokenNotificacao.getToken());
        usuarioRepository.save(usuario);
    }

    public void enviarNotificacaoDeCampanhaAberta(Campanha campanha) {
        popularDadosDaCampanha(campanha);
        List<MenssagemEnvioNotificacao> notificacoes = new ArrayList<>();

        listarUsuariosPorVacina(campanha.getVacina()).stream()
                .filter(usuario -> usuario.getMunicipio().getId().equals(campanha.getMunicipio().getId()))
                .forEach(usuario -> notificacoes.add(montarMensagemParaNotificacao(usuario, campanha)));

        enviarNotificacoes(notificacoes);
    }

    private List<Usuario> listarUsuariosPorVacina(Vacina vacina) {
        return usuarioRepository.listarUsuarioPorIdDaVacina(vacina);
    }

    private MenssagemEnvioNotificacao montarMensagemParaNotificacao(Usuario usuario, Campanha campanha) {
        return MenssagemEnvioNotificacao.builder()
                .to(usuario.getTokenNotificao())
                .title("Minha Vacina")
                .body("Campanha contra " + campanha.getVacina().getNome() + " aberta em " + campanha.getMunicipio().getNome() + " - AL")
                .sound("default")
                .data(DadosEnvioNotificao.builder().idCampanha(campanha.getId()).build())
                .build();
    }

    private void enviarNotificacoes(List<MenssagemEnvioNotificacao> notificacoes) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(EXPO_NOTIFICACAO, HttpMethod.POST, new HttpEntity<>(notificacoes), RespostaEnvioNotificacao.class);
    }

    private void popularDadosDaCampanha(Campanha campanha) {
        campanha.setMunicipio(municipioRepository.findById(campanha.getMunicipio().getId()).get());
        campanha.setVacina(vacinaRepository.findById(campanha.getVacina().getId()).get());
    }
}
