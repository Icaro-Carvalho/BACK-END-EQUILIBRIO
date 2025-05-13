package br.com.ponto.equilibrio.api.controller;

import br.com.ponto.equilibrio.api.vo.FuncionarioVO;
import br.com.ponto.equilibrio.core.service.FuncionarioService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping()
    public ResponseEntity<FuncionarioVO> cadastrarFuncionario(@RequestBody FuncionarioVO funcionarioVO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioService.cadastrarFuncionario(funcionarioVO));
    }

    @GetMapping()
    public ResponseEntity<List<FuncionarioVO>> buscarFuncionarios() {
        return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.listarFuncionarios());
    }

    @DeleteMapping("/{funcionarioId}")
    public ResponseEntity<Void> deletarFuncionario(@PathVariable Long funcionarioId) {
        funcionarioService.deletarFuncionario(funcionarioId);
        return ResponseEntity.noContent().build();
    }
}
