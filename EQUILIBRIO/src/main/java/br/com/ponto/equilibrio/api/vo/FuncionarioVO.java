package br.com.ponto.equilibrio.api.vo;

import br.com.ponto.equilibrio.api.model.Funcionario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioVO {

    @Getter
    private Long id;
    private String nome;
    private String cargo;
    private String departamento;
    private LocalDate dataAdmissao;
    private EquipeVO equipe;

    public FuncionarioVO(Funcionario funcionario) {
        this.id = funcionario.getId();
        this.nome = funcionario.getNome();
        this.cargo = funcionario.getCargo();
        this.departamento = funcionario.getDepartamento();
        this.dataAdmissao = funcionario.getDataAdmissao();
        this.equipe = new EquipeVO(funcionario.getEquipe());
    }
}
