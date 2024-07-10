package br.com.ponto.equilibrio.api.controller;

import br.com.ponto.equilibrio.api.vo.AuthenticationVO;
import br.com.ponto.equilibrio.core.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationVO authenticationVO) {
        return ResponseEntity.ok(authService.login(authenticationVO));

    }
}
