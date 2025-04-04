package br.com.ponto.equilibrio.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ponto.equilibrio.api.vo.AlertaBurnoutVO;
import br.com.ponto.equilibrio.core.service.AlertaBurnoutServie;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/alerta-burnout")
public class AlertaBurnoutController {

    @Autowired
    private AlertaBurnoutServie alertaBurnoutServie;

    @GetMapping
    public ResponseEntity<List<AlertaBurnoutVO>> listarAlertas() {
        return ResponseEntity.status(HttpStatus.OK).body(alertaBurnoutServie.listarAlertaBurnouts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<AlertaBurnoutVO>> listarAlertasPorFuncionario(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(alertaBurnoutServie.listarBurnoutPorFuncionario(id));
    }

    @PutMapping("/{id}/resolver")
    public void marcarComoResolvido(@PathVariable Long id, @Valid @RequestBody String descricao) {
        alertaBurnoutServie.marcarComoResolvido(id, descricao);
    }





    
}
