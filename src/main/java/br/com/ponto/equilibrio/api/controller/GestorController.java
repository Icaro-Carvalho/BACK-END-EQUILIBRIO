package br.com.ponto.equilibrio.api.controller;

import br.com.ponto.equilibrio.api.vo.GestorVO;
import br.com.ponto.equilibrio.core.service.GestorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gestor")
public class GestorController {

    @Autowired
    private GestorService gestorService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/cadastrar")
    public ResponseEntity<GestorVO> cadastrar(@Valid @RequestBody GestorVO gestorVO) {

        gestorVO.setSenha(passwordEncoder.encode(gestorVO.getSenha()));
        return ResponseEntity.status(HttpStatus.CREATED).body(gestorService.cadastrar(gestorVO));
    }


    @PutMapping("/editar/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable(value = "id") Long id, @Valid @RequestBody GestorVO gestorVO) {

        Optional<GestorVO> gestorById = gestorService.findById(id);

        if(gestorById.isPresent()) {
            gestorVO.setSenha(passwordEncoder.encode(gestorVO.getSenha()));
            return ResponseEntity.status(HttpStatus.OK).body(gestorService.atualizar(id, gestorVO));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gestor não encontrado");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<GestorVO>> visualizar(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(gestorService.findById(id));
    }

    @GetMapping()
    public ResponseEntity<List<GestorVO>> visualizarTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(gestorService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable(value = "id") Long id) {
        gestorService.excluir(id);
        return ResponseEntity.ok().build();
    }




}
