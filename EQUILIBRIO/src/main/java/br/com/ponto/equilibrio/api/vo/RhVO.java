package br.com.ponto.equilibrio.api.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RhVO {

    private Long id;
    private String nome;
    private String email;
}
