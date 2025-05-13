package br.com.ponto.equilibrio.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ponto.equilibrio.api.vo.AlertaBurnoutVO;
import br.com.ponto.equilibrio.api.vo.EquipeVO;
import br.com.ponto.equilibrio.api.vo.FuncionarioVO;
import br.com.ponto.equilibrio.core.service.AlertaBurnoutService;
import br.com.ponto.equilibrio.core.service.EquipeService;
import br.com.ponto.equilibrio.core.service.FuncionarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/alerta-burnout")
public class AlertaBurnoutController {

    @Autowired
    private AlertaBurnoutService alertaBurnoutServie;
    @Autowired
    private FuncionarioService funcionarioService;
    @Autowired
    private EquipeService equipeService;

    @GetMapping
    public ResponseEntity<List<AlertaBurnoutVO>> listarAlertas(@RequestParam(required = false) Long funcionarioId, @RequestParam(required = false) Long equipeId) {
        List<AlertaBurnoutVO> alertas = alertaBurnoutServie.listarAlertaBurnouts(funcionarioId, equipeId);
        return ResponseEntity.ok(alertas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<AlertaBurnoutVO>> listarAlertasPorFuncionario(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(alertaBurnoutServie.listarBurnoutPorFuncionario(id));
    }

    @PutMapping("/{id}/resolver")
    public void marcarComoResolvido(@PathVariable Long id, @Valid @RequestBody String descricao) {
        alertaBurnoutServie.marcarComoResolvido(id, descricao);
    }

    @GetMapping("/funcionarios")
    public ResponseEntity<List<FuncionarioVO>> verificarBurnoutFuncionarios() {
        List<FuncionarioVO> todos = funcionarioService.listarFuncionarios();
        List<FuncionarioVO> emRisco = alertaBurnoutServie.verificarRiscoBurnout(todos);
        return ResponseEntity.ok(emRisco);
    }

    @GetMapping("/equipes")
    public ResponseEntity<List<EquipeVO>> listarEquipesComRisco() {
        List<EquipeVO> todas = equipeService.listarEquipes();
        List<EquipeVO> emRisco = todas.stream()
            .filter(alertaBurnoutServie::analisarEquipe)
            .collect(Collectors.toList());
        return ResponseEntity.ok(emRisco);
    }

}
