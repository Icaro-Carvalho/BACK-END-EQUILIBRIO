package br.com.ponto.equilibrio.api.controller;

import br.com.ponto.equilibrio.api.vo.EmpregadorVO;
import br.com.ponto.equilibrio.core.service.EmpregadorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/empregador")
public class EmpregadorController {

    @Autowired
    private EmpregadorService empregadorService;

    @PostMapping("/cadastrar")
    public ResponseEntity<EmpregadorVO> cadastrar(@Valid @RequestBody EmpregadorVO empregadorVO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(empregadorService.cadastrar(empregadorVO));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable(value = "id") Long id, @Valid @RequestBody EmpregadorVO empregadorVO) {
        Optional<EmpregadorVO> empregadorById = empregadorService.findById(id);

        if(empregadorById.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(empregadorService.atualizar(id, empregadorVO));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empregador não encontrado");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<EmpregadorVO>> findById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(empregadorService.findById(id));
    }

    @GetMapping()
    public ResponseEntity<List<EmpregadorVO>> listarTodosEmpregadores() {
        return ResponseEntity.status(HttpStatus.OK).body(empregadorService.findall());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable(value = "id") Long id) {
        empregadorService.excluir(id);
        return ResponseEntity.ok().build();
    }
}
