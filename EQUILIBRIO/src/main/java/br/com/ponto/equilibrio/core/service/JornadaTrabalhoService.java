package br.com.ponto.equilibrio.core.service;

import br.com.ponto.equilibrio.api.model.Funcionario;
import br.com.ponto.equilibrio.api.model.JornadaTrabalho;
import br.com.ponto.equilibrio.api.vo.JornadaTrabalhoVO;
import br.com.ponto.equilibrio.core.repository.FuncionarioRepository;
import br.com.ponto.equilibrio.core.repository.JornadaTrabalhoRepository;
import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JornadaTrabalhoService {

    @Autowired
    private JornadaTrabalhoRepository jornadaTrabalhoRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public JornadaTrabalhoVO cadastrarJornada(JornadaTrabalhoVO jornadaTrabalhoVO) {
        JornadaTrabalho jornadaTrabalho = new JornadaTrabalho();
        jornadaTrabalho.setHoraEntrada(jornadaTrabalhoVO.getHoraEntrada());
        jornadaTrabalho.setHoraSaida(jornadaTrabalhoVO.getHoraSaida());
        jornadaTrabalho.setIntervaloInicio(jornadaTrabalhoVO.getIntervaloInicio());
        jornadaTrabalho.setIntervaloFim(jornadaTrabalhoVO.getIntervaloFim());
        jornadaTrabalho.setBancoHoras(jornadaTrabalhoVO.getBancoDeHoras());
        jornadaTrabalhoRepository.save(jornadaTrabalho);
        return new JornadaTrabalhoVO(jornadaTrabalho);
    }

    @Transactional
    public JornadaTrabalhoVO adicionarFuncionario(Long jornadaId, Long funcionarioId) {
        JornadaTrabalho jornada = jornadaTrabalhoRepository.findById(jornadaId)
                .orElseThrow(() -> new RuntimeException("Jornada de Trabalho não encontrada"));

        Funcionario funcionario = funcionarioRepository.findById(funcionarioId)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
        
        if (!funcionario.getJornadas().isEmpty()) {
            throw new RuntimeException("Funcionário já está vinculado a uma jornada de trabalho.");
        }

        jornada.getFuncionarios().add(funcionario);
        funcionario.getJornadas().add(jornada);

        jornadaTrabalhoRepository.save(jornada);

        return new JornadaTrabalhoVO(jornada);
    }

    @Transactional
    public JornadaTrabalhoVO removerFuncionario(Long jornadaId, Long funcionarioId) {
        JornadaTrabalho jornada = jornadaTrabalhoRepository.findById(jornadaId)
                .orElseThrow(() -> new RuntimeException("Jornada de Trabalho não encontrada"));

        Funcionario funcionario = funcionarioRepository.findById(funcionarioId)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        if (!jornada.getFuncionarios().contains(funcionario)) {
            throw new RuntimeException("Funcionário não está vinculado a esta jornada.");
        }

        jornada.getFuncionarios().remove(funcionario);
        funcionario.getJornadas().remove(jornada);

        jornadaTrabalhoRepository.save(jornada);

        return new JornadaTrabalhoVO(jornada);
    }

    public List<JornadaTrabalhoVO> listarJornadas() {
        List<JornadaTrabalho> jornadas = jornadaTrabalhoRepository.findAll();
        return jornadas.stream().map(JornadaTrabalhoVO::new).toList();
    }
}
