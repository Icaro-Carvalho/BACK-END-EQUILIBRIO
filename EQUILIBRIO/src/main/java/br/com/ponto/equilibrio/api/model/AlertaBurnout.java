package br.com.ponto.equilibrio.api.model;

import br.com.ponto.equilibrio.core.enums.StatusAlerta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ALERTAS_BURNOUT")
public class AlertaBurnout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "DATA_GERACAO")
    private LocalDateTime dataGeracao = LocalDateTime.now();
    @Column(name = "DATA_CONCLUSAO")
    private LocalDateTime dataConclusao;
    @Column(name = "DESCRICAO", nullable = false)
    private String descricao;
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private StatusAlerta status = StatusAlerta.PENDENTE;

    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;
}
