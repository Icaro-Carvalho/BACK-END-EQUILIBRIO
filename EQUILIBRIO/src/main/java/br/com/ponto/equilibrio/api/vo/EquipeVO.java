package br.com.ponto.equilibrio.api.vo;

import java.util.List;
import java.util.stream.Collectors;

import br.com.ponto.equilibrio.api.model.Equipe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipeVO {

    private Long id;
    private String nome;
    private GestorVO gestor;
    private List<FuncionarioSimplesVO> funcionarios;
    
    public EquipeVO(Equipe equipe) {
        this.id = equipe.getId();
        this.nome = equipe.getNome();
        this.gestor = new GestorVO(equipe.getGestor());
        this.funcionarios = equipe.getFuncionarios().stream().map(FuncionarioSimplesVO::new).collect(Collectors.toList());
    }
}
