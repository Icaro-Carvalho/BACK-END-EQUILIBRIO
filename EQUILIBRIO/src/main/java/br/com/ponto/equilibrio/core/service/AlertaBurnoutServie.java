package br.com.ponto.equilibrio.core.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ponto.equilibrio.api.model.AlertaBurnout;
import br.com.ponto.equilibrio.api.model.Funcionario;
import br.com.ponto.equilibrio.api.vo.AlertaBurnoutVO;
import br.com.ponto.equilibrio.core.enums.StatusAlerta;
import br.com.ponto.equilibrio.core.repository.AlertaBurnoutRepository;
import br.com.ponto.equilibrio.core.repository.FuncionarioRepository;

@Service
public class AlertaBurnoutServie {

    @Autowired
    private AlertaBurnoutRepository alertaBurnoutRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<AlertaBurnoutVO> listarAlertaBurnouts() {
        List<AlertaBurnout> alertas = alertaBurnoutRepository.findAll();
        return alertas.stream().map(AlertaBurnoutVO::new).toList();
    }

    public List<AlertaBurnoutVO> listarBurnoutPorFuncionario(Long id) {
        Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Funcionario não encontrado"));
        return alertaBurnoutRepository.findByFuncionario(funcionario).stream().map(AlertaBurnoutVO::new).toList();
    }

    public void marcarComoResolvido(Long id, String descricao) {
        AlertaBurnout alertaBurnout = alertaBurnoutRepository.getReferenceById(id);
        alertaBurnout.setStatus(StatusAlerta.RESOLVIDO);
        alertaBurnout.setDataConclusao(LocalDateTime.now());
        alertaBurnout.setDescricao(descricao);
        alertaBurnoutRepository.save(alertaBurnout);
    }
    
}
