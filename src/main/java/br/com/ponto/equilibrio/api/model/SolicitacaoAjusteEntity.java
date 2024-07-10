package br.com.ponto.equilibrio.api.model;

import br.com.ponto.equilibrio.core.enums.SituacaoSolicitacaoAjusteEnum;
import br.com.ponto.equilibrio.core.enums.TipoSolicitacaoAjusteEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TB_SOLICITACAO_AJUSTE")
public class SolicitacaoAjusteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TIPO")
    private TipoSolicitacaoAjusteEnum tipo;

    @Column(name = "DATA_HORA")
    @NotBlank
    private LocalDateTime dataHora;

    @Column(name = "JUSTIFICATIVA", length = 250)
    @NotBlank
    private String justificativa;

    @Column(name = "SITUACAO")
    @NotBlank
    private SituacaoSolicitacaoAjusteEnum situacao;

    @ManyToOne
    @JoinColumn(name = "tb_solicitacao_ajuste_tb_funcionario_fk")
    private FuncionarioEntity funcionario;

    @ManyToOne
    @JoinColumn(name = "tb_solicitacao_ajuste_tb_gestor_fk")
    private GestorEntity gestor;
}
