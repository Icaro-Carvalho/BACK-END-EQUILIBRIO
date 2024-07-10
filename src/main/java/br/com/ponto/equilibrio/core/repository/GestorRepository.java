package br.com.ponto.equilibrio.core.repository;

import br.com.ponto.equilibrio.api.model.GestorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GestorRepository extends JpaRepository<GestorEntity, Long> {

    Optional<GestorEntity> findByEmail(String email);
}
