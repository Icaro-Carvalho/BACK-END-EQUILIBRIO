package br.com.ponto.equilibrio.api.vo;

import br.com.ponto.equilibrio.api.model.AlertaBurnout;
import br.com.ponto.equilibrio.core.enums.StatusAlerta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlertaBurnoutVO {
    private Long id;
    private FuncionarioVO funcionario;
    private LocalDateTime dataGeracao;
    private LocalDateTime dataConclusao;
    private String descricao;
    private StatusAlerta status;

    public AlertaBurnoutVO(AlertaBurnout alertaBurnout) {
        this.id = alertaBurnout.getId();
        this.funcionario = new FuncionarioVO(alertaBurnout.getFuncionario());
        this.dataGeracao = alertaBurnout.getDataGeracao();
        this.dataConclusao = alertaBurnout.getDataConclusao();
        this.descricao = alertaBurnout.getDescricao();
        this.status = getStatus();
    }
}
