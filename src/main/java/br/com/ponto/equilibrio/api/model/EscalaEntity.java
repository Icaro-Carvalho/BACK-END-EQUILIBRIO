package br.com.ponto.equilibrio.api.model;

import br.com.ponto.equilibrio.core.enums.DiasDaSemanaEnum;
import br.com.ponto.equilibrio.core.enums.TipoEscalaEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_ESCALA")
public class EscalaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TIPO_ESCALA")
    private TipoEscalaEnum tipoEscala;

    //HORARIO_ESCALA - Verificar se vale a pena colocar

    @Column(name = "DIAS_TRABALHADOS")
    private DiasDaSemanaEnum diasTrabalhados;

    @Column(name = "DIAS_COMPENSADOS")
    private DiasDaSemanaEnum diasCompensados;

    @Column(name = "DESCANSO_REMUNERADO")
    private DiasDaSemanaEnum descansoRemunerado;

    @Column(name = "ENTRADA")
    @NotBlank
    private LocalTime entrada;

    @Column(name = "INICIO_INTERVALO")
    private LocalTime inicioIntervalo;

    @Column(name = "FIM_INTERVALO")
    private LocalTime fimIntervalo;

    @Column(name = "SAIDA")
    @NotBlank
    private LocalTime saida;

    @Column(name = "INICIO_TERMINO_TRABALHO")
    private LocalTime inicioTerminoTrabalho;

    @Column(name = "INTERVALO_PRE_ASSINALADO")
    private Boolean intervaloPreAssinalado;

    @Column(name = "INTERVALO_FLEXIVEL")
    private Boolean intervaloFlexivel;

    @Column(name = "COMPENSACAO_HORAS")
    private Boolean compensacaoHoras;

    @Column(name = "HORA_EXTRA_EXCLUIDA")
    private int horaExtraExcluida;

    @Column(name = "NOME", length = 150)
    @NotBlank
    private String nome;

    @Column(name = "INICIO_HORARIO_NOTURNO")
    private LocalTime inicioHorarioNoturno;

    @Column(name = "FIM_HORARIO_NOTURNO")
    private LocalTime fimHorarioNoturno;

    @OneToMany
    @JoinColumn(name = "tb_escala_tb_funcionario_fk")
    private List<FuncionarioEntity> funcionario;

    @OneToMany
    @JoinColumn(name = "tb_escala_tb_hora_extra_fk")
    private List<HoraExtraEntity> horaExtra;


}
