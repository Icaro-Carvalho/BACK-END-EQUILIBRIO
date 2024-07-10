package br.com.ponto.equilibrio.api.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationVO {

    private String email;
    private String senha;
}
