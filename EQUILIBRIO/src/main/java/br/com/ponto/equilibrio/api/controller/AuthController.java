package br.com.ponto.equilibrio.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ponto.equilibrio.api.model.Rh;
import br.com.ponto.equilibrio.api.security.JwtUtil;
import br.com.ponto.equilibrio.core.service.EmailService;
import br.com.ponto.equilibrio.core.service.RhService;
import jakarta.mail.MessagingException;

@RestController
@RequestMapping("auth")
public class AuthController {

    
    private final RhService rhService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    public AuthController(RhService rhService, PasswordEncoder passwordEncoder, EmailService emailS, JwtUtil jwtUtil) {
        this.rhService = rhService;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailS;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String senha = request.get("senha");
        String nome = request.get("nome");
        String telefone = request.get("telefone");
        Rh rh = rhService.registrar(email, senha, nome, telefone);
        return ResponseEntity.ok(rh);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String senha = request.get("senha");

        return rhService.encontrarPorEmail(email).filter(rh -> passwordEncoder.matches(senha, rh.getSenha())).map(rh -> {
            String token = jwtUtil.gerarToken(new org.springframework.security.core.userdetails.User(
                rh.getEmail(), rh.getSenha(), new ArrayList<>()
            ));

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("id", rh.getId());
            response.put("nome", rh.getNome());
            response.put("email", rh.getEmail());

            return ResponseEntity.ok(response);
        })
        .orElseThrow(() -> new RuntimeException("Credenciais inválidas"));    
    }

    @PostMapping("/esqueci-senha")
    public ResponseEntity<?> esqueciSenha(@RequestBody Map<String, String> request) throws MessagingException {
        String email = request.get("email");
        String codigo = rhService.gerarCodigoRecuperacao(email);
        emailService.enviarEmailRecuperacao(email, codigo);
        return ResponseEntity.ok(Map.of("message", "Código de recuperação enviado para o e-mail"));
    }

    @PostMapping("/validar-codigo")
    public ResponseEntity<?> validarCodigo(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String codigo = request.get("codigo");

        if (rhService.validarCodigoRecuperacao(email, codigo)) {
            return ResponseEntity.ok(Map.of("message", "Código válido."));
        } else {
            return ResponseEntity.status(400).body(Map.of("error", "Código inválido ou expirado."));
        }
    }

    @PostMapping("/trocar-senha")
    public ResponseEntity<?> trocarSenha(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String novaSenha = request.get("novaSenha");

        rhService.alterarSenha(email, novaSenha);
        return ResponseEntity.ok(Map.of("message", "Senha alterada com sucesso."));
    }
    

}
