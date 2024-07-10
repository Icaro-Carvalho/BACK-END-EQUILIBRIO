package br.com.ponto.equilibrio.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_FEEDBACK_EMOCIONAL")
public class FeedbackEmocionalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DATA_HORA")
    private LocalDateTime dataHora;

    @Column(name = "RESPOSTA", length = 600)
    private String resposta;

    @Column(name = "GRAU_SENTIMENTO")
    private int grauSentimento;

    @OneToOne
    @JoinColumn(name = "tb_feedback_emocional_tb_funcionario_fk")
    private FuncionarioEntity funcionario;

    @OneToOne
    @JoinColumn(name = "tb_feedback_emocional_tb_perguntas_feedback_FK")
    private PerguntasFeedbackEntity perguntas;
}
