package br.com.ponto.equilibrio.api.vo;

import java.time.LocalDate;

import br.com.ponto.equilibrio.api.model.Funcionario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioSimplesVO {
    private Long id;
    private String nome;
    private String cargo;
    private String departamento;
    private LocalDate dataAdmissao;

    public FuncionarioSimplesVO(Funcionario funcionario) {
        this.id = funcionario.getId();
        this.nome = funcionario.getNome();
        this.cargo = funcionario.getCargo();
        this.departamento = funcionario.getDepartamento();
        this.dataAdmissao = funcionario.getDataAdmissao();
    }
}
