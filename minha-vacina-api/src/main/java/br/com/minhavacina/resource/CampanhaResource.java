package br.com.minhavacina.resource;

import br.com.minhavacina.domain.Campanha;
import br.com.minhavacina.request.campanha.CampanhaPostRequest;
import br.com.minhavacina.request.campanha.CampanhaPutRequest;
import br.com.minhavacina.service.CampanhaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

import static br.com.minhavacina.shared.Constantes.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(CAMPANHA)
public class CampanhaResource {
    private final CampanhaService campanhaService;

    @GetMapping
    public ResponseEntity<List<Campanha>> listarCampanhasAtivas() {
        return ResponseEntity.ok(campanhaService.listarCampanhasAtivas());
    }

    @GetMapping(CAMPANHA_INATIVA)
    public ResponseEntity<List<Campanha>> listarCampanhasInativas() {
        return ResponseEntity.ok(campanhaService.listarCampanhasInativas());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Campanha> buscarCampanhaPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(campanhaService.buscarCampanhaPorId(id));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Campanha> cadastrarNovaCampanha(@RequestBody @Valid CampanhaPostRequest campanhaPostRequest) {
        return new ResponseEntity<>(campanhaService.cadastrarNovaCampanha(campanhaPostRequest), HttpStatus.CREATED);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Void> atualizarCampanha(@RequestBody @Valid CampanhaPutRequest campanhaPutRequest) {
        campanhaService.atualizarCampanha(campanhaPutRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(FINALIZA_CAMPANHA)
    @Transactional
    public ResponseEntity<Void> finalizarCampanha(@PathVariable Integer id) {
        campanhaService.finalizarCampanha(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deletarCampanha(@PathVariable Integer id) {
        campanhaService.deletarCampanha(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @Transactional
    @PutMapping(ASSOCIAR_USUARIO)
    public ResponseEntity<Void> associarUsuario(@PathVariable Integer id) {
        campanhaService.associarUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Transactional
    @PutMapping(DESASSOCIAR_USUARIO)
    public ResponseEntity<Void> desassociarUsuario(@PathVariable Integer id) {
        campanhaService.desassociarUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
