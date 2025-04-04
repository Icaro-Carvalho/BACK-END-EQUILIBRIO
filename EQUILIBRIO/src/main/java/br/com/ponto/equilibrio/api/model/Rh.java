package br.com.ponto.equilibrio.api.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "RH")
public class Rh {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NOME")
    private String nome;
    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;
    @Column(name = "SENHA")
    private String senha;
    @Column(name = "CODIGO_RECUPERACAO")
    private String codigoRecuperacao;
    @Column(name = "EXPIRACAO_CODIGO")
    private LocalDateTime expiracaoCodigo;

    public Rh(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }
}
