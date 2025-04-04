package br.com.ponto.equilibrio.api.model;

import br.com.ponto.equilibrio.api.vo.EquipeVO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "EQUIPE")
public class Equipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NOME", nullable = false)
    private String nome;
    @ManyToOne
    @JoinColumn(name = "gestor_id")
    private Gestor gestor;

    public Equipe(EquipeVO equipeVO) {
        this.id = equipeVO.getId();
        this.nome = equipeVO.getNome();
        if (equipeVO.getGestor() != null) {
            this.gestor = new Gestor(equipeVO.getGestor());
        }
    }
}
