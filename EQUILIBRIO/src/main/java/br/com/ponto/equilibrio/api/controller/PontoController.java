package br.com.ponto.equilibrio.api.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ponto.equilibrio.api.projections.HistoricoPontoProjection;
import br.com.ponto.equilibrio.api.vo.ResumoHumorDiarioVO;
import br.com.ponto.equilibrio.api.vo.ResumoHumorPeriodoVO;
import br.com.ponto.equilibrio.core.enums.Humor;
import br.com.ponto.equilibrio.core.service.PontoService;

@RestController
@RequestMapping("/ponto")
public class PontoController {

    @Autowired
    private PontoService pontoService;

    @GetMapping("/historico")
    public ResponseEntity<List<HistoricoPontoProjection>> buscarHistoricoPonto(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
            @RequestParam(required = false) Humor humor,
            @RequestParam(required = false) String funcionario,
            @RequestParam(required = false) Long equipeId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(pontoService.buscarHistorico(data, humor, funcionario, equipeId));
    }

    @GetMapping("/humor")
    public ResponseEntity<Humor[]> listarHumor() {
        return ResponseEntity.status(HttpStatus.OK).body(Humor.values());
    }

    @GetMapping("/resumo-humor")
    public ResponseEntity<ResumoHumorDiarioVO> getResumoHumorHoje() {
        return ResponseEntity.ok(pontoService.calcularResumoHumorHoje());
    }

    @GetMapping("/resumo-humor-periodo")
    public ResponseEntity<ResumoHumorPeriodoVO> getResumoHumor(@RequestParam(defaultValue = "15") int dias) {
        return ResponseEntity.ok(pontoService.calcularResumoHumorPorPeriodo(dias));
    }


}
