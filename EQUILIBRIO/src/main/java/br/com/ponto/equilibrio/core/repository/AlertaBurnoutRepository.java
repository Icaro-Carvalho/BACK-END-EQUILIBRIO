package br.com.ponto.equilibrio.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ponto.equilibrio.api.model.AlertaBurnout;
import br.com.ponto.equilibrio.api.model.Funcionario;

public interface AlertaBurnoutRepository extends JpaRepository<AlertaBurnout, Long> {

    List<AlertaBurnout> findByFuncionario(Funcionario funcionario);
    
}
