package br.com.ponto.equilibrio.api.controller;

import br.com.ponto.equilibrio.api.vo.JornadaTrabalhoVO;
import br.com.ponto.equilibrio.core.service.JornadaTrabalhoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jornada-trabalho")
public class JornadaTrabalhoController {

    @Autowired
    private JornadaTrabalhoService jornadaTrabalhoService;

    @PostMapping()
    public ResponseEntity<JornadaTrabalhoVO> cadastrarJornadaTrabalho(@RequestBody JornadaTrabalhoVO jornadaTrabalhoVO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(jornadaTrabalhoService.cadastrarJornada(jornadaTrabalhoVO));
    }

    @PutMapping("/{jornadaId}/funcionario/{funcionarioId}")
    public ResponseEntity<JornadaTrabalhoVO> adicionarFuncionario(@PathVariable Long jornadaId, @PathVariable Long funcionarioId) {
        JornadaTrabalhoVO jornadaTrabalhoVO = jornadaTrabalhoService.adicionarFuncionario(jornadaId, funcionarioId);
        return ResponseEntity.status(HttpStatus.OK).body(jornadaTrabalhoVO);
    }

    @DeleteMapping("/{jornadaId}/funcionario/{funcionarioId}")
    public ResponseEntity<JornadaTrabalhoVO> removerFuncionario(@PathVariable Long jornadaId, @PathVariable Long funcionarioId) {
        JornadaTrabalhoVO jornadaTrabalhoVO = jornadaTrabalhoService.removerFuncionario(jornadaId, funcionarioId);
        return ResponseEntity.status(HttpStatus.OK).body(jornadaTrabalhoVO);
    }

    @GetMapping()
    public ResponseEntity<List<JornadaTrabalhoVO>> listarJornadas() {
        return ResponseEntity.status(HttpStatus.OK).body(jornadaTrabalhoService.listarJornadas());
    }
}
