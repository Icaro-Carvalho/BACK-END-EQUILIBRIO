package br.com.ponto.equilibrio.core.repository;

import br.com.ponto.equilibrio.api.model.FuncionarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, Long> {

    boolean existsByCpf(String cpf);
}
