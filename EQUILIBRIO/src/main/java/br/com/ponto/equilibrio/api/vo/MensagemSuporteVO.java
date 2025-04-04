package br.com.ponto.equilibrio.api.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MensagemSuporteVO {
    private Long id;
    private Long rhId;
    private Long funcionarioId;
    private String mensagem;
    private LocalDateTime dataEnvio;
}
