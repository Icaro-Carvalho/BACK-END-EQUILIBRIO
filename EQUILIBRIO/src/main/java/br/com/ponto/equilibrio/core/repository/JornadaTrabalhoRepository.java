package br.com.ponto.equilibrio.core.repository;

import br.com.ponto.equilibrio.api.model.JornadaTrabalho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface JornadaTrabalhoRepository extends JpaRepository<JornadaTrabalho, Long> {
}
