package br.com.ponto.equilibrio.api.model;

import br.com.ponto.equilibrio.api.vo.EmpregadorVO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_EMPREGADOR")
public class EmpregadorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "RAZAO_SOCIAL", length = 200, unique = true)
    private String razaoSocial;

    @Column(name = "CNPJ_CPF", length = 14, unique = true)
    private String cnpjCpf;

    @Column(name = "CEP", length = 8)
    private String cep;

    @Column(name = "LOGRADOURO", length = 200)
    private String logradouro;

    @Column(name = "NUMERO", length = 6)
    private String numero;

    @Column(name = "COMPLEMENTO", length = 100)
    private String complemento;

    @Column(name = "ESTADO", length = 20)
    private String estado;

    @Column(name = "CIDADE", length = 50)
    private String cidade;

    @Column(name = "DIA_FECHAMENTO_PONTO")
    private int diaFechamentoPonto;

    public EmpregadorEntity(EmpregadorVO empregadorVO) {
        this.id = empregadorVO.getId();
        this.razaoSocial = empregadorVO.getRazaoSocial();
        this.cnpjCpf = empregadorVO.getCnpjCpf();
        this.cep = empregadorVO.getCep();
        this.logradouro = empregadorVO.getLogradouro();
        this.numero = empregadorVO.getNumero();
        this.complemento = empregadorVO.getComplemento();
        this.estado = empregadorVO.getEstado();
        this.cidade = empregadorVO.getCidade();
        this.diaFechamentoPonto = empregadorVO.getDiaFechamentoPonto();

    }




}
