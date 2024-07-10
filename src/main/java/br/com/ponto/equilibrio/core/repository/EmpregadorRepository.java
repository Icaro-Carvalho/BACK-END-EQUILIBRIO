package br.com.ponto.equilibrio.core.repository;

import br.com.ponto.equilibrio.api.model.EmpregadorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpregadorRepository extends JpaRepository<EmpregadorEntity, Long> {
}
