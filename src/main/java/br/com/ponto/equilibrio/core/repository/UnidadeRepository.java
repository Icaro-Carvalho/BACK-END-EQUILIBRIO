package br.com.ponto.equilibrio.core.repository;

import br.com.ponto.equilibrio.api.model.UnidadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UnidadeRepository extends JpaRepository<UnidadeEntity, Long> {


}
