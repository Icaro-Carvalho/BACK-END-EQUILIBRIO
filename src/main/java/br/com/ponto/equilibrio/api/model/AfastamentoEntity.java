package br.com.ponto.equilibrio.api.model;

import br.com.ponto.equilibrio.core.enums.TipoAfastamentoEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_AFASTAMENTO")
public class AfastamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TIPO_AFASTAMENTO")
    private TipoAfastamentoEnum tipoAfastamento;

    @Column(name = "LICENCA_REMUNERADA")
    private Boolean licencaRemunerada;

    @Column(name = "DATA_INICIO")
    private LocalDate dataInicio;

    @Column(name = "DATA_FIM")
    private LocalDate dataFim;

    @Column(name = "DETALHAMENTO", length = 300)
    private String detalhamento;

    @Column(name = "HORA_INICIO")
    private LocalTime horaInicio;

    @ManyToOne
    @JoinColumn(name = "tb_afastamento_tb_funcionario_fk")
    private FuncionarioEntity funcionario;

    @ManyToOne
    @JoinColumn(name = "tb_afastamento_tb_unidade_fk")
    private UnidadeEntity unidade;
}
