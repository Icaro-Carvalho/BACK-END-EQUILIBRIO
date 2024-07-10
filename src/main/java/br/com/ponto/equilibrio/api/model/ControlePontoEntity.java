package br.com.ponto.equilibrio.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_CONTROLE_PONTO")
public class ControlePontoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DATA")
    private LocalDate data;

    @Column(name = "HORA_ENTRADA")
    private LocalTime horaEntrada;

    @Column(name = "HORA_SAIDA")
    private LocalTime horaSaida;

    @Column(name = "HORA_ENTRADA_ALMOCO")
    private LocalTime horaEntradaAlmoco;

    @Column(name = "HORA_SAIDA_ALMOCO")
    private LocalTime horaSaidaAlmoco;

    @Column(name = "HORAS_TRABALHADAS")
    private float horasTrabalhadas;

    @Column(name = "SENTIMENTO")
    private int sentimento;

    @ManyToOne
    @JoinColumn(name = "tb_controle_ponto_tb_funcionario_fk")
    private FuncionarioEntity funcionario;
}
