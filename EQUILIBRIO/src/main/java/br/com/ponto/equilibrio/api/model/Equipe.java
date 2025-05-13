package br.com.ponto.equilibrio.api.model;

import java.util.ArrayList;
import java.util.List;

import br.com.ponto.equilibrio.api.vo.EquipeVO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
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
    @OneToMany(mappedBy = "equipe")
    private List<Funcionario> funcionarios = new ArrayList<>();

    public Equipe(EquipeVO equipeVO) {
        this.id = equipeVO.getId();
        this.nome = equipeVO.getNome();
        if (equipeVO.getGestor() != null) {
            this.gestor = new Gestor(equipeVO.getGestor());
        }
    }
}
