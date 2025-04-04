package br.com.ponto.equilibrio.api.vo;

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

    public EquipeVO(Equipe equipe) {
        this.id = equipe.getId();
        this.nome = equipe.getNome();
        this.gestor = new GestorVO(equipe.getGestor());
    }
}
