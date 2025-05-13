package br.com.ponto.equilibrio.core.service;

import br.com.ponto.equilibrio.api.model.Equipe;
import br.com.ponto.equilibrio.api.model.Funcionario;
import br.com.ponto.equilibrio.api.vo.FuncionarioVO;

import br.com.ponto.equilibrio.core.repository.EquipeRepository;
import br.com.ponto.equilibrio.core.repository.FuncionarioRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private EquipeRepository equipeRepository;

    public FuncionarioVO cadastrarFuncionario(FuncionarioVO funcionarioVO) {
        Equipe equipe = equipeRepository.findById(funcionarioVO.getEquipe().getId()).orElseThrow(() -> new RuntimeException("Equipe não encontrada"));
        System.out.println("Equipe carregada: " + equipe);
        System.out.println("Gestor da equipe: " + equipe.getGestor());
        Funcionario funcionario = new Funcionario(funcionarioVO);
        funcionario.setEquipe(equipe);
        funcionarioRepository.save(funcionario);
        return new FuncionarioVO(funcionario);
    }

    public List<FuncionarioVO> listarFuncionarios() {
        List<Funcionario> alertas = funcionarioRepository.findAll();
        return alertas.stream().map(FuncionarioVO::new).toList();
    }

    public void deletarFuncionario(Long funcionarioId) {
        if (!funcionarioRepository.existsById(funcionarioId)) {
            throw new EntityNotFoundException("Funcionário não encontrado");
        }
        funcionarioRepository.deleteById(funcionarioId);
    }




}
