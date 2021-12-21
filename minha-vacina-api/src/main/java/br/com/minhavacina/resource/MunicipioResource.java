package br.com.minhavacina.resource;

import br.com.minhavacina.domain.Municipio;
import br.com.minhavacina.service.MunicipioService;
import br.com.minhavacina.shared.Constantes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(Constantes.MUNICIPIO)
public class MunicipioResource {
    private final MunicipioService municipioService;

    @GetMapping
    public ResponseEntity<List<Municipio>> listarTodosOsMunicipios() {
        return ResponseEntity.ok(municipioService.listarTodosOsMunicipios());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Municipio> buscarMunicipioPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(municipioService.buscarMunicipioPorId(id));
    }
}
