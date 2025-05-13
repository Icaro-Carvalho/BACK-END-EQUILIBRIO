package br.com.ponto.equilibrio.api.model;

import br.com.ponto.equilibrio.api.vo.FuncionarioVO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FUNCIONARIO")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NOME", nullable = false)
    private String nome;
    @Column(name = "CPF_CNPJ", nullable = false)
    private String cpfCnpj;
    @Column(name = "TELEFONE")
    private String telefone;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "CARGO")
    private String cargo;
    @Column(name = "DEPARTAMENTO")
    private String departamento;
    @Column(name = "DATA_ADMISSAO", nullable = false)
    private LocalDate dataAdmissao;
    @ManyToOne
    @JoinColumn(name = "equipe_id")
    private Equipe equipe;
    @ManyToMany(mappedBy = "funcionarios")
    private Set<JornadaTrabalho> jornadas = new HashSet<>();
    

    public Funcionario(FuncionarioVO funcionarioVO) {
        this.id = funcionarioVO.getId();
        this.nome = funcionarioVO.getNome();
        this.cpfCnpj = funcionarioVO.getCpfCnpj();
        this.telefone = funcionarioVO.getTelefone();
        this.email = funcionarioVO.getEmail();
        this.cargo = funcionarioVO.getCargo();
        this.departamento = funcionarioVO.getDepartamento();
        this.dataAdmissao = funcionarioVO.getDataAdmissao();
        this.equipe = new Equipe(funcionarioVO.getEquipe());

    }
}
