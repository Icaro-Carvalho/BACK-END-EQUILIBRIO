package br.com.ponto.equilibrio.core.repository;

import br.com.ponto.equilibrio.api.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    boolean existsByEquipeId(Long equipeId);

    boolean existsByJornadasId(Long jornadaId);
}
