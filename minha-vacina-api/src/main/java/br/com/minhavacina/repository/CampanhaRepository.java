package br.com.minhavacina.repository;

import br.com.minhavacina.domain.Campanha;
import br.com.minhavacina.domain.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CampanhaRepository extends JpaRepository<Campanha, Integer> {

    @Query("select c from Campanha c where c.ativa = true")
    List<Campanha> listarCampanhasAtivas();

    @Query("select c from Campanha c where c.ativa = false")
    List<Campanha> listarCampanhasInativas();

    @Query("select c from Campanha c where c.municipio = :municipio and c.dataFim >= :dataAtual")
    List<Campanha> listarCampanhasPorMunicipio(@Param("municipio") Municipio municipio, @Param("dataAtual") Date dataAtual);
}
