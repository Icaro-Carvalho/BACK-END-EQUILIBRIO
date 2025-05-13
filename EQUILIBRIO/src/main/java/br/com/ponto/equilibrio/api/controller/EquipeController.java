package br.com.ponto.equilibrio.api.controller;

import br.com.ponto.equilibrio.api.vo.EquipeVO;
import br.com.ponto.equilibrio.core.service.EquipeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping()
    public ResponseEntity<List<EquipeVO>> buscarEquipes() {
        return ResponseEntity.status(HttpStatus.OK).body(equipeService.listarEquipes());
    }

    @PutMapping("/{equipeId}/funcionarios")
    public ResponseEntity<EquipeVO> adicionarFuncionarios(@PathVariable Long equipeId, @RequestBody List<Long> funcionariosIds) {
        EquipeVO equipeVO = equipeService.adicionarFuncionarios(equipeId, funcionariosIds);
        return ResponseEntity.status(HttpStatus.OK).body(equipeVO);
    }

    @PutMapping("/{equipeId}/gestor")
    public ResponseEntity<EquipeVO> adicionarGestor(@PathVariable Long equipeId, @RequestBody Long gestorId) {
        EquipeVO equipeVO = equipeService.adicionarGestor(equipeId, gestorId);
        return ResponseEntity.status(HttpStatus.OK).body(equipeVO);
    }

    @DeleteMapping("/{equipeId}")
    public ResponseEntity<Void> deletarEquipe(@PathVariable Long equipeId) {
        equipeService.deletarEquipe(equipeId);
        return ResponseEntity.noContent().build();
    }

}
