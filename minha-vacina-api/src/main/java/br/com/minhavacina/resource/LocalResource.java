package br.com.minhavacina.resource;

import br.com.minhavacina.domain.Local;
import br.com.minhavacina.request.local.LocalFiltroDescricaoPostRequest;
import br.com.minhavacina.request.local.LocalPostRequest;
import br.com.minhavacina.request.local.LocalPutRequest;
import br.com.minhavacina.service.LocalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

import static br.com.minhavacina.shared.Constantes.FILTROS.POR_DESCRICAO;
import static br.com.minhavacina.shared.Constantes.LOCAL;

@RestController
@RequestMapping(LOCAL)
@RequiredArgsConstructor
public class LocalResource {
    private final LocalService localService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<Local> buscarLocalPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(localService.buscarLocalPorId(id));
    }

    @GetMapping(path = "/municipio/{id}")
    public ResponseEntity<List<Local>> listarLocaisPeloMunicipio(@PathVariable Integer id) {
        return ResponseEntity.ok(localService.listarLocaisPeloMunicipio(id));
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Local> cadastrarNovoLocal(@RequestBody @Valid LocalPostRequest localPostRequest) {
        return new ResponseEntity<>(localService.cadastrarNovoLocal(localPostRequest), HttpStatus.CREATED);
    }

    @PostMapping(POR_DESCRICAO)
    public ResponseEntity<List<Local>> listarLocaisPorDescricao
            (@RequestBody @Valid LocalFiltroDescricaoPostRequest localFiltroDescricaoPostRequest) {
        return ResponseEntity.ok(localService.listarLocaisPorDescricaoEMunicipio(localFiltroDescricaoPostRequest));
    }

    @Transactional
    @PutMapping
    public ResponseEntity<Void> atualizarLocal(@RequestBody @Valid LocalPutRequest localPutRequest) {
        localService.atualizarLocal(localPutRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deletarLocal(@PathVariable Integer id) {
        localService.deletarLocal(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
