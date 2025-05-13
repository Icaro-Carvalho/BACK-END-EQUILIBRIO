package br.com.ponto.equilibrio.api.projections;

import java.time.LocalDate;
import java.time.LocalTime;

import br.com.ponto.equilibrio.core.enums.Humor;
import br.com.ponto.equilibrio.core.enums.TipoPonto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoricoPontoProjection {

    private LocalDate data;
    private LocalTime hora;
    private TipoPonto tipo;
    private String funcionario;
    private String equipe;
    private Humor humor;

    
}
