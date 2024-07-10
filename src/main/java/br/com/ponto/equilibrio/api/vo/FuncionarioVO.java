package br.com.ponto.equilibrio.api.vo;

import br.com.ponto.equilibrio.api.model.FuncionarioEntity;
import br.com.ponto.equilibrio.api.model.GestorEntity;
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
public class FuncionarioVO {

    private Long id;

    @NotBlank
    private String nomeCompleto;

    private String cpf;

    private LocalDate dataNascimento;

    private String nisPis;

    private String celular;

    private String email;

    @NotBlank
    private String estado;

    @NotBlank
    private String cidade;

    private String departamento;

    private String cargo;

    private String codigoMatricula;

    private BigDecimal valorHora;

    @NotNull
    private LocalDate dataAdmissao;

    private LocalDate inicioRegistroPonto;

    private LocalDate fimRegistroPonto;

    private EmpregadorVO empregador;

    private List<GestorVO> gestor;

    private UnidadeVO unidade;

    public FuncionarioVO(FuncionarioEntity funcionarioEntity) {
        this.id = funcionarioEntity.getId();
        this.nomeCompleto = funcionarioEntity.getNomeCompleto();
        this.cpf = funcionarioEntity.getCpf();
        this.dataNascimento = funcionarioEntity.getDataNascimento();
        this.nisPis = funcionarioEntity.getNisPis();
        this.celular = funcionarioEntity.getCelular();
        this.email = funcionarioEntity.getEmail();
        this.estado = funcionarioEntity.getEstado();
        this.cidade = funcionarioEntity.getCidade();
        this.departamento = funcionarioEntity.getDepartamento();
        this.cargo = funcionarioEntity.getCargo();
        this.codigoMatricula = funcionarioEntity.getCodigoMatricula();
        this.valorHora = funcionarioEntity.getValorHora();
        this.dataAdmissao = funcionarioEntity.getDataAdmissao();
        this.inicioRegistroPonto = funcionarioEntity.getInicioRegistroPonto();
        this.fimRegistroPonto = funcionarioEntity.getFimRegistroPonto();
        this.empregador = new EmpregadorVO(funcionarioEntity.getEmpregador());
        this.gestor = funcionarioEntity.getGestor().stream().map(GestorVO::new).collect(Collectors.toList());
        this.unidade = new UnidadeVO(funcionarioEntity.getUnidade());
    }

}
