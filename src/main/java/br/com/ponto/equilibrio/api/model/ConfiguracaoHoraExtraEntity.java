package br.com.ponto.equilibrio.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_CONFIGURACAO_HORA_EXTRA")
public class ConfiguracaoHoraExtraEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "PRIMEIRAS_HORAS")
    private int primeirasHoras;

    @Column(name = "PERCENTUAL_PRIMEIRAS_HORAS")
    private float percentualPrimeirasHoras;

    @Column(name = "PERCENTUAL_DEMAIS_HORAS")
    private float percentualDemaisHoras;
}
