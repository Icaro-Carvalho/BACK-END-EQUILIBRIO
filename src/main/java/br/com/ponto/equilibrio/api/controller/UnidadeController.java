package br.com.ponto.equilibrio.api.controller;

import br.com.ponto.equilibrio.api.vo.UnidadeVO;
import br.com.ponto.equilibrio.core.service.UnidadeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/unidade")
public class UnidadeController {

    @Autowired
    private UnidadeService unidadeService;

    @PostMapping("/cadastrar")
    public ResponseEntity<UnidadeVO> cadastrar(@Valid @RequestBody UnidadeVO unidadeVO){
        return ResponseEntity.status(HttpStatus.CREATED).body(unidadeService.cadastrar(unidadeVO));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable(value = "id") Long id, @Valid @RequestBody UnidadeVO unidadeVO) {

        Optional<UnidadeVO> findById = unidadeService.findById(id);

        if(findById.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(unidadeService.atualizar(id, unidadeVO));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Unidade não encontrada");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<UnidadeVO>> visualizar(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(unidadeService.findById(id));
    }

    @GetMapping()
    public ResponseEntity<List<UnidadeVO>> visualizarTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(unidadeService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable(value = "id") Long id) {
        unidadeService.excluir(id);
        return ResponseEntity.ok().build();
    }
}
