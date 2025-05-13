package br.com.ponto.equilibrio.core.service;

import java.time.LocalDate;
import java.util.Map;
import java.util.function.Function;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ponto.equilibrio.api.model.Equipe;
import br.com.ponto.equilibrio.api.model.Funcionario;
import br.com.ponto.equilibrio.api.model.Ponto;
import br.com.ponto.equilibrio.api.projections.HistoricoPontoProjection;
import br.com.ponto.equilibrio.api.vo.PontoVO;
import br.com.ponto.equilibrio.api.vo.ResumoHumorDiarioVO;
import br.com.ponto.equilibrio.api.vo.ResumoHumorPeriodoVO;
import br.com.ponto.equilibrio.core.enums.Humor;
import br.com.ponto.equilibrio.core.repository.PontoRepository;

@Service
public class PontoService {

    @Autowired
    private PontoRepository pontoRepository;
   

    public List<PontoVO> listarPontos() {
        List<Ponto> pontos = pontoRepository.findAll();
        return pontos.stream().map(PontoVO::new).toList();
    }

    public List<HistoricoPontoProjection> buscarHistorico(LocalDate data, Humor humor, String funcionario, Long equipeId) {
        return pontoRepository.buscarHistoricoPonto(data, humor, funcionario, equipeId);
    }

    public ResumoHumorDiarioVO calcularResumoHumorHoje() {
        LocalDate hoje = LocalDate.now();
        List<Ponto> pontosDoDia = pontoRepository.findByData(hoje);

        Map<Funcionario, List<Ponto>> pontosPorFuncionario = pontosDoDia.stream()
            .collect(Collectors.groupingBy(Ponto::getFuncionario));

        Map<Humor, Long> resumo = new EnumMap<>(Humor.class);

        for (List<Ponto> pontosFuncionario : pontosPorFuncionario.values()) {
            Map<Humor, Long> contagem = pontosFuncionario.stream()
                .collect(Collectors.groupingBy(Ponto::getHumor, Collectors.counting()));

            Humor humorPredominante = determinarHumorPredominante(contagem);
            resumo.put(humorPredominante, resumo.getOrDefault(humorPredominante, 0L) + 1);
        }

        return new ResumoHumorDiarioVO(resumo);
    }

    public ResumoHumorPeriodoVO calcularResumoHumorPorPeriodo(int dias) {
        LocalDate hoje = LocalDate.now();
        LocalDate dataInicio = hoje.minusDays(dias - 1);

        List<Ponto> pontos = pontoRepository.findByDataBetween(dataInicio, hoje);

        Map<LocalDate, List<Ponto>> pontosPorData = pontos.stream()
            .collect(Collectors.groupingBy(Ponto::getData));

        Map<Humor, Long> resumoFuncionarios = new EnumMap<>(Humor.class);
        Map<Humor, Long> resumoEquipes = new EnumMap<>(Humor.class);

        for (List<Ponto> pontosDoDia : pontosPorData.values()) {

            // Agrupa pontos por funcionário
            Map<Funcionario, List<Ponto>> pontosPorFuncionario = pontosDoDia.stream()
                .collect(Collectors.groupingBy(Ponto::getFuncionario));

            // Armazena o humor predominante de cada funcionário
            Map<Funcionario, Humor> humorPorFuncionario = new HashMap<>();

            for (Map.Entry<Funcionario, List<Ponto>> entry : pontosPorFuncionario.entrySet()) {
                Humor humorFuncionario = determinarHumorPredominante(entry.getValue().stream()
                    .collect(Collectors.groupingBy(Ponto::getHumor, Collectors.counting())));
                humorPorFuncionario.put(entry.getKey(), humorFuncionario);
                resumoFuncionarios.put(humorFuncionario, resumoFuncionarios.getOrDefault(humorFuncionario, 0L) + 1);
            }

            // Agrupa os humores dos funcionários por equipe
            Map<Equipe, List<Humor>> humoresPorEquipe = humorPorFuncionario.entrySet().stream()
                .collect(Collectors.groupingBy(
                    e -> e.getKey().getEquipe(),
                    Collectors.mapping(Map.Entry::getValue, Collectors.toList())
                ));

            for (List<Humor> humores : humoresPorEquipe.values()) {
                Map<Humor, Long> contagem = humores.stream()
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

                Humor humorEquipe = determinarHumorPredominante(contagem);
                resumoEquipes.put(humorEquipe, resumoEquipes.getOrDefault(humorEquipe, 0L) + 1);
            }
        }

        return new ResumoHumorPeriodoVO(resumoFuncionarios, resumoEquipes);
    }


    private Humor determinarHumorPredominante(Map<Humor, Long> contagem) {
        long max = contagem.values().stream().mapToLong(v -> v).max().orElse(0);
    
        List<Humor> maisFrequentes = contagem.entrySet().stream()
            .filter(e -> e.getValue() == max)
            .map(Map.Entry::getKey)
            .toList();
    
        if (maisFrequentes.size() == 1) {
            return maisFrequentes.get(0);
        }
    
        return Humor.NEUTRO;
    }
    
}
