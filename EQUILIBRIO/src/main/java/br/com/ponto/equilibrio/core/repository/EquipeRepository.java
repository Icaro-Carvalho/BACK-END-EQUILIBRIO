package br.com.ponto.equilibrio.core.repository;

import br.com.ponto.equilibrio.api.model.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipeRepository extends JpaRepository<Equipe, Long> {
}
