package br.com.ponto.equilibrio.api.vo;

import br.com.ponto.equilibrio.api.model.UnidadeEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UnidadeVO {

    private Long id;

    @NotBlank
    private String nome;

    private String fusoHorario;

    private String endereco;

    private double latitude;

    private double longitude;

    private double raio;

    public UnidadeVO(UnidadeEntity unidadeEntity) {
        this.id = unidadeEntity.getId();
        this.nome = unidadeEntity.getNome();
        this.fusoHorario = unidadeEntity.getFusoHorario();
        this.endereco = unidadeEntity.getEndereco();
        this.latitude = unidadeEntity.getLatitude();
        this.longitude = unidadeEntity.getLongitude();
        this.raio = unidadeEntity.getRaio();
    }

    public UnidadeVO(Long id) {
        this.id = id;
    }
}
