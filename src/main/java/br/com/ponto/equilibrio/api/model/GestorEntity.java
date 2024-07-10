package br.com.ponto.equilibrio.api.model;

import br.com.ponto.equilibrio.api.vo.GestorVO;
import br.com.ponto.equilibrio.core.enums.PermissaoEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TB_GESTOR")
public class GestorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOME_COMPLETO", length = 200)
    @NotBlank
    private String nomeCompleto;

    @Column(name = "CELULAR", length = 13)
    @NotBlank
    private String celular;

    @Column(name = "EMAIL", length = 256, unique = true)
    @NotBlank
    private String email;

    @Column(name = "ATENDIMENTO_CHAT")
    private Boolean atendimentoChat;

    @Column(name = "PERMISSAO")
    private PermissaoEnum permissao;

    @Column(name = "SENHA")
    private String senha;

    public GestorEntity(GestorVO gestorVO) {
        this.id = gestorVO.getId();
        this.nomeCompleto = gestorVO.getNomeCompleto();
        this.celular = gestorVO.getCelular();
        this.email = gestorVO.getEmail();
        this.atendimentoChat = gestorVO.getAtendimentoChat();
        this.permissao = gestorVO.getPermissao();
        this.senha = gestorVO.getSenha();
    }
}
