package br.com.ponto.equilibrio.api.vo;

import br.com.ponto.equilibrio.api.model.EmpregadorEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmpregadorVO {

    private Long id;
    private String razaoSocial;
    private String cnpjCpf;
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String estado;
    private String cidade;
    private int diaFechamentoPonto;

    public EmpregadorVO(EmpregadorEntity empregadorEntity) {
        this.id = empregadorEntity.getId();
        this.razaoSocial = empregadorEntity.getRazaoSocial();
        this.cnpjCpf = empregadorEntity.getCnpjCpf();
        this.cep = empregadorEntity.getCep();
        this.logradouro = empregadorEntity.getLogradouro();
        this.numero = empregadorEntity.getNumero();
        this.complemento = empregadorEntity.getComplemento();
        this.estado = empregadorEntity.getEstado();
        this.cidade = empregadorEntity.getCidade();
        this.diaFechamentoPonto = empregadorEntity.getDiaFechamentoPonto();
    }
}
