package br.com.ponto.equilibrio.core.service;

import br.com.ponto.equilibrio.api.model.Funcionario;
import br.com.ponto.equilibrio.api.model.JornadaTrabalho;
import br.com.ponto.equilibrio.api.vo.JornadaTrabalhoVO;
import br.com.ponto.equilibrio.core.repository.FuncionarioRepository;
import br.com.ponto.equilibrio.core.repository.JornadaTrabalhoRepository;
import jakarta.persistence.EntityNotFoundException;
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
    @Autowired
    private JornadaTrabalhoRepository jornadaRepository;

    public JornadaTrabalhoVO cadastrarJornada(JornadaTrabalhoVO jornadaTrabalhoVO) {
        JornadaTrabalho jornadaTrabalho = new JornadaTrabalho();
        jornadaTrabalho.setHoraEntrada(jornadaTrabalhoVO.getHoraEntrada());
        jornadaTrabalho.setHoraSaida(jornadaTrabalhoVO.getHoraSaida());
        jornadaTrabalho.setIntervaloInicio(jornadaTrabalhoVO.getIntervaloInicio());
        jornadaTrabalho.setIntervaloFim(jornadaTrabalhoVO.getIntervaloFim());
        jornadaTrabalhoRepository.save(jornadaTrabalho);
        return new JornadaTrabalhoVO(jornadaTrabalho);
    }

    @Transactional
    public JornadaTrabalhoVO adicionarFuncionarios(Long jornadaId, List<Long> funcionariosIds) {
        JornadaTrabalho jornada = jornadaTrabalhoRepository.findById(jornadaId).orElseThrow(() -> new RuntimeException("Jornada de Trabalho não encontrada"));

        List<Funcionario> funcionarios = funcionarioRepository.findAllById(funcionariosIds);

        if (funcionarios.size() != funcionariosIds.size()) {
            throw new RuntimeException("Um ou mais funcionários não foram encontrados.");
        }

        for (Funcionario funcionario : funcionarios) {
            if (!funcionario.getJornadas().isEmpty()) {
                throw new RuntimeException("Funcionário " + funcionario.getId() + " já está vinculado a uma jornada de trabalho.");
            }

            jornada.getFuncionarios().add(funcionario);
            funcionario.getJornadas().add(jornada);
        }

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

    public void deletarJornada(Long jornadaId) {
        JornadaTrabalho jornada = jornadaRepository.findById(jornadaId).orElseThrow(() -> new EntityNotFoundException("Jornada não encontrada"));

        boolean funcionariosVinculados = funcionarioRepository.existsByJornadasId(jornadaId);

        if (funcionariosVinculados) {
            throw new IllegalStateException("Não é possível excluir a jornada com funcionários vinculados.");
        }

        jornadaRepository.delete(jornada);
    }
}
