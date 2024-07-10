package br.com.ponto.equilibrio.api.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AcessVO {

    private String token;

    //TODO implementar retornar o usuario e liberacoes (authorities)
}
