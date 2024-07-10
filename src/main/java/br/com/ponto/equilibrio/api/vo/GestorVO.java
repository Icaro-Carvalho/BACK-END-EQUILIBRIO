package br.com.ponto.equilibrio.api.vo;

import br.com.ponto.equilibrio.api.model.GestorEntity;
import br.com.ponto.equilibrio.core.enums.PermissaoEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GestorVO {

    private Long id;
    @NotBlank
    private String nomeCompleto;
    @NotBlank
    private String celular;
    @NotBlank
    private String email;
    private Boolean atendimentoChat;
    private PermissaoEnum permissao;
    private String senha;

    public GestorVO(GestorEntity gestorEntity) {
        this.id = gestorEntity.getId();
        this.nomeCompleto = gestorEntity.getNomeCompleto();
        this.celular = gestorEntity.getCelular();
        this.email = gestorEntity.getEmail();
        this.atendimentoChat = gestorEntity.getAtendimentoChat();
        this.permissao = gestorEntity.getPermissao();
        this.senha = gestorEntity.getSenha();
    }
}
