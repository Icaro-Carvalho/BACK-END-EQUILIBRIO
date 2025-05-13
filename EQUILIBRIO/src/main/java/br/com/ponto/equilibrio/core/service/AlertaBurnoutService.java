package br.com.ponto.equilibrio.core.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ponto.equilibrio.api.model.AlertaBurnout;
import br.com.ponto.equilibrio.api.model.Funcionario;
import br.com.ponto.equilibrio.api.model.Ponto;
import br.com.ponto.equilibrio.api.vo.AlertaBurnoutVO;
import br.com.ponto.equilibrio.api.vo.EquipeVO;
import br.com.ponto.equilibrio.api.vo.FuncionarioVO;
import br.com.ponto.equilibrio.core.enums.Humor;
import br.com.ponto.equilibrio.core.enums.StatusAlerta;
import br.com.ponto.equilibrio.core.repository.AlertaBurnoutRepository;
import br.com.ponto.equilibrio.core.repository.FuncionarioRepository;
import br.com.ponto.equilibrio.core.repository.PontoRepository;

@Service
public class AlertaBurnoutService {

    @Autowired
    private AlertaBurnoutRepository alertaBurnoutRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired 
    private PontoRepository pontoRepository;

    public List<AlertaBurnoutVO> listarAlertaBurnouts(Long funcionarioId, Long equipeId) {
        List<AlertaBurnout> alertas = alertaBurnoutRepository.findByFiltros(StatusAlerta.PENDENTE, funcionarioId, equipeId);
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

    public boolean analisarFuncionario(Funcionario funcionario) {
        LocalDate hoje = LocalDate.now();
        LocalDate inicioJanela = hoje.minusDays(14);
        LocalDate dataPrimeiroPonto = pontoRepository.findFirstByFuncionarioOrderByDataAsc(funcionario)
            .map(Ponto::getData)
            .orElse(hoje);

        if (dataPrimeiroPonto.plusDays(15).isAfter(hoje)) {
            return false;
        }

        List<Ponto> pontosRecentes = pontoRepository
            .findByFuncionarioAndDataBetweenOrderByDataAscHoraAsc(funcionario, inicioJanela, hoje);

        if (pontosRecentes.isEmpty()) return false;

        double mediaJanela = pontosRecentes.stream()
            .mapToInt(p -> p.getHumor().getScore())
            .average().orElse(0);

        double mediaHistorica = pontoRepository
            .findByFuncionario(funcionario).stream()
            .mapToInt(p -> p.getHumor().getScore())
            .average().orElse(0);

        long estressadoCount = pontosRecentes.stream()
            .filter(p -> p.getHumor() == Humor.ESTRESSADO)
            .count();

        boolean tendenciaNegativa = pontosRecentes.stream()
            .map(p -> p.getHumor().getScore())
            .reduce((prev, curr) -> curr < prev ? curr : prev)
            .orElse(0) < 0;

        return mediaJanela < -1
                || mediaJanela < mediaHistorica - 1
                || estressadoCount >= 5
                || tendenciaNegativa;
    }

    public boolean analisarEquipe(EquipeVO equipeVO) {
        List<Funcionario> membros = equipeVO.getFuncionarios().stream()
            .map(vo -> funcionarioRepository.getReferenceById(vo.getId()))
            .collect(Collectors.toList());

        if (membros.size() < 2) {
            return false; 
        }

        int membrosEmRisco = 0;

        for (Funcionario f : membros) {
            if (analisarFuncionario(f)) {
                membrosEmRisco++;
            }
        }

        return membrosEmRisco >= (membros.size() / 2);
    }

    private void criarAlertaSeNecessario(Funcionario funcionario) {
        boolean jaTemAlertaPendente = alertaBurnoutRepository
            .findByFuncionario(funcionario)
            .stream()
            .anyMatch(a -> a.getStatus() == StatusAlerta.PENDENTE);
    
        if (!jaTemAlertaPendente) {
            AlertaBurnout alerta = new AlertaBurnout();
            alerta.setFuncionario(funcionario);
            alerta.setDataGeracao(LocalDateTime.now());
            alertaBurnoutRepository.save(alerta);
        }
    }

    public List<FuncionarioVO> verificarRiscoBurnout(List<FuncionarioVO> funcionariosVO) {
        List<FuncionarioVO> emRisco = new ArrayList<>();
    
        for (FuncionarioVO vo : funcionariosVO) {
            Funcionario funcionario = funcionarioRepository.findById(vo.getId()).orElseThrow(() -> new RuntimeException("Funcionario não encontrado"));
    
            if (analisarFuncionario(funcionario)) {
                criarAlertaSeNecessario(funcionario);
                emRisco.add(new FuncionarioVO(funcionario));
            }
        }
    
        return emRisco;
    }
    
}
