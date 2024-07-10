package br.com.ponto.equilibrio.api.controller;

import br.com.ponto.equilibrio.api.vo.FuncionarioVO;
import br.com.ponto.equilibrio.core.service.FuncionarioService;
import br.com.ponto.equilibrio.core.service.UnidadeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<FuncionarioVO> cadastrar(@Valid @RequestBody FuncionarioVO funcionarioVO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioService.cadastrar(funcionarioVO));
    }


}
