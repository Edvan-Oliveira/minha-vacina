package br.com.minhavacina.service;

import br.com.minhavacina.domain.Local;
import br.com.minhavacina.domain.Municipio;
import br.com.minhavacina.domain.Usuario;
import br.com.minhavacina.exception.LancarAdvertencia;
import br.com.minhavacina.mapper.LocalMapper;
import br.com.minhavacina.repository.LocalRepository;
import br.com.minhavacina.request.local.LocalFiltroDescricaoPostRequest;
import br.com.minhavacina.request.local.LocalPostRequest;
import br.com.minhavacina.request.local.LocalPutRequest;
import br.com.minhavacina.util.Utilitaria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.minhavacina.util.Utilitaria.objetoEstarNuloOuVazio;

@Service
@RequiredArgsConstructor
public class LocalService {
    private final LocalMapper localMapper;
    private final LocalRepository localRepository;
    private final MunicipioService municipioService;

    public List<Local> listarLocaisPeloMunicipio(Integer id) {
        return localRepository.listarLocaisPeloMunicipio(municipioService.buscarMunicipioPorId(id));
    }

    public Local buscarLocalPorId(Integer id) {
        return localRepository.findById(id)
                .orElseThrow(() -> new LancarAdvertencia("Local não encontrado"));
    }

    public Local cadastrarNovoLocal(LocalPostRequest localPostRequest) {
        return localRepository.save(localMapper.converterParaLocal(localPostRequest));
    }

    public List<Local> listarLocaisPorDescricaoEMunicipio(LocalFiltroDescricaoPostRequest localFiltroDescricaoPostRequest) {
        if (objetoEstarNuloOuVazio(localFiltroDescricaoPostRequest.getMunicipio().getId())){
            throw new LancarAdvertencia("Id de município está nulo");
        }
        return localRepository.findByDescricaoContainsIgnoreCaseAndMunicipioEquals
                (localFiltroDescricaoPostRequest.getDescricao(), localFiltroDescricaoPostRequest.getMunicipio());
    }

    public void atualizarLocal(LocalPutRequest localPutRequest) {
        buscarLocalPorId(localPutRequest.getId());
        localRepository.save(localMapper.converterParaLocal(localPutRequest));
    }

    public void deletarLocal(Integer id) {
        localRepository.delete(buscarLocalPorId(id));
    }
}
