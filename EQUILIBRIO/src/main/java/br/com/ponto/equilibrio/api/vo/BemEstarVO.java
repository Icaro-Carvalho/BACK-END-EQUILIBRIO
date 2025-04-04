package br.com.ponto.equilibrio.api.vo;

import br.com.ponto.equilibrio.core.enums.Humor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BemEstarVO {
    private Long id;
    private Long funcionarioId;
    private LocalDate data;
    private Humor humorMedio;
    private String reclamacoes;
}
