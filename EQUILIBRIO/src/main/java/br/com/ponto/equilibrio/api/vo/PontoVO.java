package br.com.ponto.equilibrio.api.vo;

import br.com.ponto.equilibrio.api.model.Ponto;
import br.com.ponto.equilibrio.core.enums.Humor;
import br.com.ponto.equilibrio.core.enums.TipoPonto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PontoVO {
    private Long id;
    private FuncionarioVO funcionario;
    private LocalDate data;
    private LocalTime hora;
    private TipoPonto tipo;
    private Humor humor;
    private String mensagem;

    public PontoVO(Ponto ponto) {
        this.id = ponto.getId();
        this.funcionario = new FuncionarioVO(ponto.getFuncionario());
        this.data = ponto.getData();
        this.hora = ponto.getHora();
        this.tipo = ponto.getTipo();
        this.humor = ponto.getHumor();
        this.mensagem = ponto.getMensagem();
    }
}
