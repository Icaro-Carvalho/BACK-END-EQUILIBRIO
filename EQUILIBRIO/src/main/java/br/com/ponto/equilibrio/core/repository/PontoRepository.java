package br.com.ponto.equilibrio.core.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.ponto.equilibrio.api.model.Funcionario;
import br.com.ponto.equilibrio.api.model.Ponto;
import br.com.ponto.equilibrio.api.projections.HistoricoPontoProjection;
import br.com.ponto.equilibrio.core.enums.Humor;

@Repository
public interface PontoRepository extends JpaRepository<Ponto, Long>{

    List<Ponto> findByFuncionario(Funcionario funcionario);

    List<Ponto> findByData(LocalDate data);

    List<Ponto> findByFuncionarioAndDataBetweenOrderByDataAscHoraAsc(Funcionario funcionario, LocalDate inicio, LocalDate fim);

    @Query("SELECT new br.com.ponto.equilibrio.api.projections.HistoricoPontoProjection(p.data, p.hora, p.tipo, f.nome, e.nome, p.humor)" 
           + " FROM Ponto p JOIN p.funcionario f JOIN f.equipe e"
           + " WHERE (:data IS NULL OR p.data = :data)"
           + " AND (:humor IS NULL OR p.humor = :humor)"
           + " AND (:funcionario IS NULL OR LOWER(f.nome) LIKE LOWER(CONCAT('%', :funcionario, '%')))"
           + " AND (:equipeId IS NULL OR e.id = :equipeId)"
           )
    List<HistoricoPontoProjection> buscarHistoricoPonto(@Param("data") LocalDate data, @Param("humor") Humor humor, @Param("funcionario") String funcionario, @Param("equipeId") Long equipeId);

    List<Ponto> findByDataBetween(LocalDate inicio, LocalDate fim);

    Optional<Ponto> findFirstByFuncionarioOrderByDataAsc(Funcionario funcionario);

}
