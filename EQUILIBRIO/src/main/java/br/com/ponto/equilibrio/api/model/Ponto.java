package br.com.ponto.equilibrio.api.model;

import br.com.ponto.equilibrio.core.enums.Humor;
import br.com.ponto.equilibrio.core.enums.TipoPonto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PONTO")
public class Ponto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "DATA", nullable = false)
    private LocalDate data;
    @Column(name = "HORA", nullable = false)
    private LocalTime hora;
    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO", nullable = false)
    private TipoPonto tipo;
    @Enumerated(EnumType.STRING)
    @Column(name = "HUMOR", nullable = false)
    private Humor humor;
    @Column(name = "MENSAGEM")
    private String mensagem;
    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;
}
