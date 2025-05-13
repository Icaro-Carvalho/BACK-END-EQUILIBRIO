package br.com.ponto.equilibrio.api.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
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
    @Column(name = "TELEFONE")
    private String telefone;
    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;
    @Column(name = "SENHA")
    private String senha;
    @Column(name = "CODIGO_RECUPERACAO")
    private String codigoRecuperacao;
    @Column(name = "EXPIRACAO_CODIGO")
    private LocalDateTime expiracaoCodigo;

    public Rh(String email, String senha, String nome, String telefone) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.telefone = telefone;
    }
}
