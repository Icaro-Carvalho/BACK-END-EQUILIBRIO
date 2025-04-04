package br.com.ponto.equilibrio.api.model;

import br.com.ponto.equilibrio.api.vo.FuncionarioVO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FUNCIONARIO")
public class Funcionario {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NOME", nullable = false)
    private String nome;
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
        this.cargo = funcionarioVO.getCargo();
        this.departamento = funcionarioVO.getDepartamento();
        this.dataAdmissao = funcionarioVO.getDataAdmissao();
        this.equipe = new Equipe(funcionarioVO.getEquipe());

    }
}
