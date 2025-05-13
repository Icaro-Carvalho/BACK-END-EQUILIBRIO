package br.com.ponto.equilibrio.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.ponto.equilibrio.api.model.AlertaBurnout;
import br.com.ponto.equilibrio.api.model.Funcionario;
import br.com.ponto.equilibrio.core.enums.StatusAlerta;

public interface AlertaBurnoutRepository extends JpaRepository<AlertaBurnout, Long> {

    List<AlertaBurnout> findByFuncionario(Funcionario funcionario);

    List<AlertaBurnout> findByStatus(StatusAlerta status);

    @Query("SELECT a FROM AlertaBurnout a"
         + " WHERE a.status = :status"
         + " AND (:funcionarioId IS NULL OR a.funcionario.id = :funcionarioId)"
         + " AND (:equipeId IS NULL OR a.funcionario.equipe.id = :equipeId)"
    )
    List<AlertaBurnout> findByFiltros(@Param("status") StatusAlerta status, @Param("funcionarioId") Long funcionarioId, @Param("equipeId") Long equipeId);
    
}
