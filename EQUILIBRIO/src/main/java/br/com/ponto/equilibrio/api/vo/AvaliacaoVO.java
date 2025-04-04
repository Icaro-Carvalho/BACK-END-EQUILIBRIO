package br.com.ponto.equilibrio.api.vo;

import java.time.LocalDateTime;

import br.com.ponto.equilibrio.api.model.Avaliacao;
import br.com.ponto.equilibrio.core.enums.Humor;
import br.com.ponto.equilibrio.core.enums.TipoAvaliacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvaliacaoVO {
    private Long id;
    private TipoAvaliacao tipo;
    private Humor humor;
    private String pergunta;
    private LocalDateTime dataAvaliacao;
    private EquipeVO equipeVO;

    public AvaliacaoVO(Avaliacao avaliacao) {
        this.id = avaliacao.getId();
        this.tipo = avaliacao.getTipo();
        this.humor = avaliacao.getHumor();
        this.pergunta = avaliacao.getPergunta();
        this.dataAvaliacao = avaliacao.getDataAvaliacao();
        this.equipeVO = new EquipeVO(avaliacao.getEquipe());
    }
    
}
