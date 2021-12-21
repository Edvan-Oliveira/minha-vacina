package br.com.minhavacina.mapper;

import br.com.minhavacina.domain.Usuario;
import br.com.minhavacina.request.usuario.UsuarioGetRequest;
import br.com.minhavacina.request.usuario.UsuarioLoginRequest;
import br.com.minhavacina.request.usuario.UsuarioPostRequest;
import br.com.minhavacina.request.usuario.UsuarioPutRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UsuarioMapper {
    public static final UsuarioMapper INSTANCIA = Mappers.getMapper(UsuarioMapper.class);
    public abstract Usuario converterParaUsuario(UsuarioPostRequest usuarioPostRequest);
    public abstract Usuario converterParaUsuario(UsuarioPutRequest usuarioPutRequest);
    public abstract Usuario converterParaUsuario(UsuarioLoginRequest usuarioLoginRequest);
    public abstract UsuarioGetRequest converterParaUsuarioGetRequest(Usuario usuario);
    public abstract List<UsuarioGetRequest> converterParaListaUsuarioGetRequest(List<Usuario> usuarios);
}
