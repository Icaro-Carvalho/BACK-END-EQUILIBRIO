package br.com.ponto.equilibrio.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ponto.equilibrio.api.model.Equipe;
import br.com.ponto.equilibrio.api.model.Funcionario;
import br.com.ponto.equilibrio.api.model.Ponto;
import br.com.ponto.equilibrio.api.vo.PontoVO;
import br.com.ponto.equilibrio.core.repository.EquipeRepository;
import br.com.ponto.equilibrio.core.repository.FuncionarioRepository;
import br.com.ponto.equilibrio.core.repository.PontoRepository;

@Service
public class PontoService {

    @Autowired
    private PontoRepository pontoRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private EquipeRepository equipeRepository;

    public List<PontoVO> listarPontoPorFuncionario(Long id) {
        Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Funcionario não encontrado"));
        return pontoRepository.findByFuncionario(funcionario).stream().map(PontoVO::new).toList();
    }

    public List<PontoVO> listarPontoPorEquipe(Long id) {
        Equipe equipe = equipeRepository.findById(id).orElseThrow(() -> new RuntimeException("Equipe não encontrada"));
        return pontoRepository.findByEquipe(equipe).stream().map(PontoVO::new).toList();
    }

    public List<PontoVO> listarPontos() {
        List<Ponto> pontos = pontoRepository.findAll();
        return pontos.stream().map(PontoVO::new).toList();
    }
    
}
