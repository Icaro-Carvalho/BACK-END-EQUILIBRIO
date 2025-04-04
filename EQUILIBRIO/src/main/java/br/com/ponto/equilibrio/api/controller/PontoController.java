package br.com.ponto.equilibrio.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ponto.equilibrio.api.vo.PontoVO;
import br.com.ponto.equilibrio.core.service.PontoService;

@RestController
@RequestMapping("/ponto")
public class PontoController {

    @Autowired
    private PontoService pontoService;

    @GetMapping()
    public ResponseEntity<List<PontoVO>> listarPonto() {
        return ResponseEntity.status(HttpStatus.OK).body(pontoService.listarPontos());
    }

    @GetMapping("/funcionario/{id}")
    public ResponseEntity<List<PontoVO>> listarPontosPorFuncionario(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(pontoService.listarPontoPorFuncionario(id));
    }

    @GetMapping("/equipe/{id}")
    public ResponseEntity<List<PontoVO>> listarPontosPorEquipe(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(pontoService.listarPontoPorEquipe(id));
    }
    
}
