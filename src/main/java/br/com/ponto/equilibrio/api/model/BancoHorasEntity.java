package br.com.ponto.equilibrio.api.model;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity(name = "TB_BANCO_HORAS")
public class BancoHorasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "UTILIZA_BANCO")
    private Boolean utilizaBanco;

    @Column(name = "DEBITAR_ATRASO")
    private Boolean debitarAtraso;

    @Column(name = "DEBITAR_FALTA")
    private Boolean debitarFalta;

    @Column(name = "CREDITAR_DIAS_NORMAIS")
    private Boolean creditarDiasNormais;

    @Column(name = "CREDITA_DIA_FOLGAS")
    private Boolean creditarDiaFolgas;

    @Column(name = "CREDITAR_DIA_COMPENSADOS")
    private Boolean creditarDiaCompensados;

    @Column(name = "CREDITAR_FERIADOS")
    private Boolean creditarFeriados;

    @Column(name = "CREDITAR_DESCANSO_REMUNERADO")
    private Boolean creditarDescansoRemunerado;

    @Column(name = "CREDITAR_DIAS_LIVRES")
    private Boolean creditarDiasLivres;

    @Column(name = "LIMITE_TEMPO_DIARIO")
    private Boolean limiteTempoDiaria;

    @Column(name = "LIMITE_TODOS_OS_DIAS")
    private LocalTime limiteTodosOsDias;

    @Column(name = "LIMITE_DIAS_NORMAIS")
    private LocalTime limiteDiasNormais;

    @Column(name = "LIMITE_DIAS_COMPENSADOS")
    private LocalTime limiteDiasCompensados;

    @Column(name = "LIMITE_DIAS_LIVRES")
    private LocalTime limiteDiasLivres;

    @Column(name = "LIMITE_FOLGA")
    private LocalTime limiteFolga;

    @Column(name = "LIMITE_FERIADO")
    private LocalTime limiteFeriado;

    @Column(name = "LIMITE_DESCANSO_REMUNERADO")
    private LocalTime limiteDescansoRemunerado;

    @ManyToOne
    @JoinColumn(name = "tb_banco_horas_tb_empregador_fk")
    private EmpregadorEntity empregador;

    @OneToOne
    @JoinColumn(name = "tb_banco_horas_tb_funcionario_fk")
    private FuncionarioEntity funcionario;

    


}
