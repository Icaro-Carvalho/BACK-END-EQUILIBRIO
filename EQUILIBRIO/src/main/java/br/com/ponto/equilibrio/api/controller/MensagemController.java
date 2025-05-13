package br.com.ponto.equilibrio.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ponto.equilibrio.api.projections.MensagemRequest;
import br.com.ponto.equilibrio.api.vo.MensagemVO;
import br.com.ponto.equilibrio.core.service.MensagemService;

@RestController
@RequestMapping("/mensagem")
public class MensagemController {

    @Autowired
    private MensagemService mensagemService;

    @PostMapping()
    public ResponseEntity<MensagemVO> criarMensagem(@RequestBody MensagemVO mensagemVO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mensagemService.criarMensagem(mensagemVO));
    }

    @GetMapping()
    public ResponseEntity<List<MensagemVO>> buscarMensagens() {
        return ResponseEntity.status(HttpStatus.OK).body(mensagemService.mostrarMensagens());
    }

    @PostMapping("/enviar")
    public ResponseEntity<Void> enviarMensagem(@RequestBody MensagemRequest mensagemRequest) {
        try {
            mensagemService.enviarMensagem(mensagemRequest.getRhId(), mensagemRequest.getFuncionarioId(), mensagemRequest.getMensagemId());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
}
