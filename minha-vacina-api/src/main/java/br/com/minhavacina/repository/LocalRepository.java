package br.com.minhavacina.repository;

import br.com.minhavacina.domain.Local;
import br.com.minhavacina.domain.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalRepository extends JpaRepository<Local, Integer> {

    @Query("select l from Local l where l.municipio = :municipio")
    List<Local> listarLocaisPeloMunicipio(@Param("municipio") Municipio municipio);

    List<Local> findByDescricaoContainsIgnoreCaseAndMunicipioEquals(String descricao, Municipio municipio);
}
