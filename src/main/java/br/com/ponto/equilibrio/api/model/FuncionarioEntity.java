package br.com.ponto.equilibrio.api.model;

import br.com.ponto.equilibrio.api.vo.FuncionarioVO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_FUNCIONARIO")
public class FuncionarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOME_COMPLETO", length = 200)
    @NotBlank
    private String nomeCompleto;

    @Column(name = "CPF", length = 11, unique = true)
    private String cpf;

    @Column(name = "DATA_NASCIMENTO")
    private LocalDate dataNascimento;

    @Column(name = "NIS_PIS", length = 11, unique = true)
    private String nisPis;

    @Column(name = "CELULAR", length = 13)
    private String celular;

    @Column(name = "EMAIL", length = 320)
    private String email;

    @Column(name = "ESTADO", length = 20)
    @NotBlank
    private String estado;

    @Column(name = "CIDADE", length = 35)
    @NotBlank
    private String cidade;

    @Column(name = "DEPARTAMENTO", length = 200)
    private String departamento;

    @Column(name = "CARGO", length = 200)
    private String cargo;

    @Column(name = "CODIGO_MATRICULA", length = 100)
    private String codigoMatricula;

    @Column(name = "VALOR_HORA")
    @NotNull
    private BigDecimal valorHora;

    @Column(name = "DATA_ADMISSAO")
    @NotNull
    private LocalDate dataAdmissao;

    @Column(name = "INICIO_REGISTRO_PONTO")
    @NotNull
    private LocalDate inicioRegistroPonto;

    @Column(name = "FIM_REGISTRO_PONTO")
    private LocalDate fimRegistroPonto;

    @ManyToOne
    @JoinColumn(name = "tb_funcionario_tb_empregador_fk")
    private EmpregadorEntity empregador;

    @OneToMany
    @JoinColumn(name = "tb_funcionario_tb_gestor_fk")
    private List<GestorEntity> gestor;

    @ManyToOne
    @JoinColumn(name = "tb_funcionario_tb_unidade_fk")
    private UnidadeEntity unidade;

    public FuncionarioEntity(FuncionarioVO funcionarioVO) {
        this.id = funcionarioVO.getId();
        this.nomeCompleto = funcionarioVO.getNomeCompleto();
        this.cpf = funcionarioVO.getCpf();
        this.dataNascimento = funcionarioVO.getDataNascimento();
        this.nisPis = funcionarioVO.getNisPis();
        this.celular = funcionarioVO.getCelular();
        this.email = funcionarioVO.getEmail();
        this.estado = funcionarioVO.getEstado();
        this.cidade = funcionarioVO.getCidade();
        this.departamento = funcionarioVO.getDepartamento();
        this.cargo = funcionarioVO.getCargo();
        this.codigoMatricula = funcionarioVO.getCodigoMatricula();
        this.valorHora = funcionarioVO.getValorHora();
        this.dataAdmissao = funcionarioVO.getDataAdmissao();
        this.inicioRegistroPonto = funcionarioVO.getInicioRegistroPonto();
        this.fimRegistroPonto = funcionarioVO.getFimRegistroPonto();
        this.empregador = new EmpregadorEntity(funcionarioVO.getEmpregador());
        this.gestor = funcionarioVO.getGestor().stream().map(GestorEntity::new).collect(Collectors.toList());
        this.unidade = new UnidadeEntity(funcionarioVO.getUnidade());
    }
}
