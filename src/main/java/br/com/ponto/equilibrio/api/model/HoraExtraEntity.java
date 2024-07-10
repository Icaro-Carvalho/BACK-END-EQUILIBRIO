package br.com.ponto.equilibrio.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TB_HORA_EXTRA")
public class HoraExtraEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "diasNormais", referencedColumnName = "id")
    private ConfiguracaoHoraExtraEntity diasNormais;

    @ManyToOne
    @JoinColumn(name = "diasCompensados", referencedColumnName = "id")
    private ConfiguracaoHoraExtraEntity diasCompensados;

    @ManyToOne
    @JoinColumn(name = "diasLivres", referencedColumnName = "id")
    private ConfiguracaoHoraExtraEntity diasLivres;

    @ManyToOne
    @JoinColumn(name = "diasFolgas", referencedColumnName = "id")
    private ConfiguracaoHoraExtraEntity diasFolga;

    @ManyToOne
    @JoinColumn(name = "feriados", referencedColumnName = "id")
    private ConfiguracaoHoraExtraEntity feriado;

    @ManyToOne
    @JoinColumn(name = "diasDescansoRemunerado", referencedColumnName = "id")
    private ConfiguracaoHoraExtraEntity diasDescansoRemunerado;

    @ManyToOne
    @JoinColumn(name = "tb_hora_extra_tb_funcionario_FK")
    private FuncionarioEntity funcionario;
}
