package br.com.ponto.equilibrio.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ponto.equilibrio.api.model.MensagemSuporte;

@Repository
public interface MensagemSuporteRepository extends JpaRepository<MensagemSuporte, Long> {
    
}
