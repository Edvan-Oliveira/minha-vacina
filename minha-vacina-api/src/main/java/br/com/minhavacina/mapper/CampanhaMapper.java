package br.com.minhavacina.mapper;

import br.com.minhavacina.domain.Campanha;
import br.com.minhavacina.request.campanha.CampanhaPostRequest;
import br.com.minhavacina.request.campanha.CampanhaPutRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class CampanhaMapper {
    public static final CampanhaMapper INSTANCIA = Mappers.getMapper(CampanhaMapper.class);
    public abstract Campanha converterParaCampanha(CampanhaPostRequest campanhaPostRequest);
    public abstract Campanha converterParaCampanha(CampanhaPutRequest campanhaPutRequest);
}
