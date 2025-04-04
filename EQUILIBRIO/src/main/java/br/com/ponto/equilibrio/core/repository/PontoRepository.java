package br.com.ponto.equilibrio.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ponto.equilibrio.api.model.Equipe;
import br.com.ponto.equilibrio.api.model.Funcionario;
import br.com.ponto.equilibrio.api.model.Ponto;

@Repository
public interface PontoRepository extends JpaRepository<Ponto, Long>{

    List<Ponto> findByFuncionario(Funcionario funcionario);
    
}
