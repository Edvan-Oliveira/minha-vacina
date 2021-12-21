package br.com.minhavacina.repository;

import br.com.minhavacina.domain.Usuario;
import br.com.minhavacina.domain.Vacina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findByEmail(String email);

    @Query("select u from Vacina v join v.usuarios u where v = :vacina")
    List<Usuario> listarUsuarioPorIdDaVacina(@Param("vacina") Vacina vacina);
}
