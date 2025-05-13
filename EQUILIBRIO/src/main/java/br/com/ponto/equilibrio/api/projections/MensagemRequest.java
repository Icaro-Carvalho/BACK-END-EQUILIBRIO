package br.com.ponto.equilibrio.api.projections;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class MensagemRequest {
    private Long rhId;
    private Long funcionarioId;
    private Long mensagemId;
}
