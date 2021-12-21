package br.com.minhavacina.service;

import br.com.minhavacina.domain.Usuario;
import br.com.minhavacina.domain.Vacina;
import br.com.minhavacina.exception.LancarAdvertencia;
import br.com.minhavacina.repository.UsuarioRepository;
import br.com.minhavacina.repository.VacinaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.minhavacina.util.Utilitaria.obterIdDoUsuarioAutenticado;
import static br.com.minhavacina.util.Utilitaria.obterUsuarioAutenticado;

@Service
@RequiredArgsConstructor
public class VacinaService {

    private final VacinaRepository vacinaRepository;
    private final UsuarioRepository usuarioRepository;

    public List<Vacina> listarTodasAsVacinas() {
        return vacinaRepository.findAll();
    }

    public Vacina buscarVacinaPorId(Integer id) {
        return vacinaRepository.findById(id)
                .orElseThrow(() -> new LancarAdvertencia("Vacina não encontrada"));
    }

    public void associarUsuario(Integer idVacina) {
        Vacina vacina = buscarVacinaPorId(idVacina);
        Usuario usuario = usuarioRepository.findById(obterIdDoUsuarioAutenticado()).get();
        usuario.getVacinas().add(vacina);
        usuarioRepository.save(usuario);
    }
    public void desassociarUsuario(Integer idVacina) {
        Vacina vacina = buscarVacinaPorId(idVacina);
        Usuario usuario = usuarioRepository.findById(obterIdDoUsuarioAutenticado()).get();
        usuario.getVacinas().remove(vacina);
        usuarioRepository.save(usuario);
    }
}
