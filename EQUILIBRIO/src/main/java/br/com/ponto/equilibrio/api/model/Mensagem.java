package br.com.ponto.equilibrio.api.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.ponto.equilibrio.api.vo.MensagemVO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MENSAGEM")
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TEXTO", nullable = false)
    private String texto;

    @Column(name = "DATA_CRIACAO")
    private LocalDate dataCriacao;

    public Mensagem(MensagemVO mensagem) {
        this.id = mensagem.getId();
        this.texto = mensagem.getTexto();
        this.dataCriacao = mensagem.getDataCriacao();
    }

}