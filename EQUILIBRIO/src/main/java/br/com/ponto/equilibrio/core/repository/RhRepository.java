package br.com.ponto.equilibrio.core.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ponto.equilibrio.api.model.Rh;

@Repository
public interface RhRepository extends JpaRepository<Rh, Long> {

    Optional<Rh> findByEmail(String email);
    
}
