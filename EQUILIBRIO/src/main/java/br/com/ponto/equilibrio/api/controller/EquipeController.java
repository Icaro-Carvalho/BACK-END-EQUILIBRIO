package br.com.ponto.equilibrio.api.controller;

import br.com.ponto.equilibrio.api.vo.EquipeVO;
import br.com.ponto.equilibrio.core.service.EquipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/equipe")
public class EquipeController {

    @Autowired
    private EquipeService equipeService;

    @PostMapping()
    public ResponseEntity<EquipeVO> cadastrarEquipe(@RequestBody EquipeVO equipeVO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(equipeService.cadastrarEquipe(equipeVO));
    }
}
