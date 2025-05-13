package br.com.ponto.equilibrio.api.vo;

import java.time.LocalDate;

import br.com.ponto.equilibrio.api.model.Mensagem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MensagemVO {
    private Long id;
    private String texto;
    private LocalDate dataCriacao;

    public MensagemVO(Mensagem mensagem) {
        this.id = mensagem.getId();
        this.texto = mensagem.getTexto();
        this.dataCriacao = mensagem.getDataCriacao();
    }
    
}
