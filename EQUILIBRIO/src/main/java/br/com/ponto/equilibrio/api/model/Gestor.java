package br.com.ponto.equilibrio.api.model;

import br.com.ponto.equilibrio.api.vo.GestorVO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "GESTOR")
public class Gestor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NOME", nullable = false)
    private String nome;
    @Column(name = "EMAIL", unique = true, nullable = false)
    private String email;

    public Gestor(GestorVO gestorVO) {
        this.id = gestorVO.getId();
        this.nome = gestorVO.getNome();
        this.email = gestorVO.getEmail();
    }
}
