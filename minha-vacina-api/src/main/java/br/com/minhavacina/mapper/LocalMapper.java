package br.com.minhavacina.mapper;

import br.com.minhavacina.domain.Local;
import br.com.minhavacina.request.local.LocalPostRequest;
import br.com.minhavacina.request.local.LocalPutRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class LocalMapper {
    public static final LocalMapper INSTANCIA = Mappers.getMapper(LocalMapper.class);
    public abstract Local converterParaLocal(LocalPostRequest localPostRequest);
    public abstract Local converterParaLocal(LocalPutRequest localPutRequest);
}
