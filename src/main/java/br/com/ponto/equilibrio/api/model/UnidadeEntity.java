package br.com.ponto.equilibrio.api.model;

import br.com.ponto.equilibrio.api.vo.UnidadeVO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_UNIDADE")
public class UnidadeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOME", length = 150)
    @NotBlank
    private String nome;

    @Column(name= "FUSO_HORARIO", length = 50)
    @NotBlank
    private String fusoHorario;

    @Column(name = "ENDERECO", length = 255)
    private String endereco;

    @Column(name = "LATITUDE")
    private double latitude;

    @Column(name = "LONGITUDE")
    private double longitude;

    @Column(name = "RAIO")
    private double raio;

    public UnidadeEntity(UnidadeVO unidadeVO) {
        this.id = unidadeVO.getId();
        this.nome = unidadeVO.getNome();
        this.fusoHorario = unidadeVO.getFusoHorario();
        this.endereco = unidadeVO.getEndereco();
        this.latitude = unidadeVO.getLatitude();
        this.longitude = unidadeVO.getLongitude();
        this.raio = unidadeVO.getRaio();
    }

    public UnidadeEntity(Long id) {
        this.id = id;
    }

}
