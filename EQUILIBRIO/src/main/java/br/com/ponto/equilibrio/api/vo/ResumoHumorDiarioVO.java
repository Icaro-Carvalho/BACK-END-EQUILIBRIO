package br.com.ponto.equilibrio.api.vo;

import java.util.Map;

import br.com.ponto.equilibrio.core.enums.Humor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResumoHumorDiarioVO {

    private Map<Humor, Long> totalPorHumor;
    
}
