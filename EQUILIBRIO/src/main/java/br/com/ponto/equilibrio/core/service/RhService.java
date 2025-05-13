package br.com.ponto.equilibrio.core.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.Random;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.ponto.equilibrio.api.model.Rh;
import br.com.ponto.equilibrio.core.repository.RhRepository;

@Service
public class RhService {

    private final RhRepository rhRepository;
    private final PasswordEncoder passwordEncoder;

    public RhService(RhRepository rhRepository, PasswordEncoder passwordEncoder) {
        this.rhRepository = rhRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Rh registrar(String email, String senha, String nome, String telefone) {
        String senhaHash = passwordEncoder.encode(senha);
        Rh rh = new Rh(email, senhaHash, nome, telefone);
        return rhRepository.save(rh);
    }

    public Optional<Rh> encontrarPorEmail(String email) {
        return rhRepository.findByEmail(email);
    }

    public String gerarCodigoRecuperacao(String email) {
        Rh rh = rhRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("RH não encontrado"));

        String codigo = String.format("%04d", new Random().nextInt(10000)); 

        LocalDateTime expiracao = LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).plusMinutes(5); 

        rh.setCodigoRecuperacao(codigo);
        rh.setExpiracaoCodigo(expiracao);
        rhRepository.save(rh);

        return codigo;
    }

    public boolean validarCodigoRecuperacao(String email, String codigo) {
        Rh rh = rhRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("RH não encontrado"));

        if (rh.getCodigoRecuperacao() == null || rh.getExpiracaoCodigo() == null) {
            return false;
        }

        return codigo.equals(rh.getCodigoRecuperacao()) && rh.getExpiracaoCodigo().isAfter(LocalDateTime.now());
    }

    public void alterarSenha(String email, String novaSenha) {
        Optional<Rh> rhOptional = rhRepository.findByEmail(email);
        if (rhOptional.isPresent()) {
            Rh rh = rhOptional.get();
            rh.setSenha(passwordEncoder.encode(novaSenha));
            rhRepository.save(rh);
        } else {
            throw new RuntimeException("Usuário não encontrado");
        }
    }
    
}
