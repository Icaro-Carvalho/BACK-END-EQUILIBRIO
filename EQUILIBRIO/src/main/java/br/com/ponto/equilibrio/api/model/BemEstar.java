package br.com.ponto.equilibrio.api.model;

import br.com.ponto.equilibrio.core.enums.Humor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BEM_ESTAR")
public class BemEstar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "DATA", nullable = false)
    private LocalDate data;
    @Enumerated(EnumType.STRING)
    @Column(name = "HUMOR_MEDIO")
    private Humor humorMedio;
    @Column(name = "RECLAMACOES")
    private String reclamacoes;

    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;
}
