package br.com.ponto.equilibrio.api.model;

import br.com.ponto.equilibrio.core.enums.Humor;
import br.com.ponto.equilibrio.core.enums.TipoAvaliacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "AVALIACOES")
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO")
    private TipoAvaliacao tipo;
    @Column(name = "HUMOR")
    private Humor humor;
    @Column(name = "PERGUNTA")
    private String pergunta;
    @Column(name = "RESPOSTA")
    private String resposta;
    @Column(name = "DATA_AVALIACAO")
    private LocalDateTime dataAvaliacao;
    @ManyToOne
    @JoinColumn(name = "equipe_id")
    private Equipe equipe;
}
