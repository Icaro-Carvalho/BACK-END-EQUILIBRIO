package br.com.ponto.equilibrio.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TB_SOBREAVISO")
public class SobreavisoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DATA_INICIO")
    @NotBlank
    private LocalDate dataInicio;

    @Column(name = "DATA_FIM")
    @NotBlank
    private LocalDate dataFim;

    @Column(name = "HORA_INICIO")
    private LocalTime horaInicio;

    @Column(name = "HORA_FIM")
    private LocalTime horaFim;

    @ManyToOne
    @JoinColumn(name = "tb_sobreaviso_tb_funcionario_fk")
    private FuncionarioEntity funcionario;
}
