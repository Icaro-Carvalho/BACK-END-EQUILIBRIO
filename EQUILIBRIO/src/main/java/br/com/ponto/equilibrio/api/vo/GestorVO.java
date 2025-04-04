package br.com.ponto.equilibrio.api.vo;

import br.com.ponto.equilibrio.api.model.Gestor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GestorVO {

    private Long id;
    private String nome;
    private String email;

    public GestorVO(Gestor gestor) {
        this.id = gestor.getId();
        this.nome = gestor.getNome();
        this.email = gestor.getEmail();
    }

}
