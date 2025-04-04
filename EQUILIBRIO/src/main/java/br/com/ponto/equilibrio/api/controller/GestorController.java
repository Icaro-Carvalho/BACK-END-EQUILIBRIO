package br.com.ponto.equilibrio.api.controller;

import br.com.ponto.equilibrio.api.vo.FuncionarioVO;
import br.com.ponto.equilibrio.api.vo.GestorVO;
import br.com.ponto.equilibrio.core.service.GestorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gestor")
public class GestorController {

    @Autowired
    private GestorService gestorService;

    @PostMapping()
    public ResponseEntity<GestorVO> cadastrarGestor(@RequestBody GestorVO gestorVO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gestorService.cadastrarGestor(gestorVO));
    }
}
