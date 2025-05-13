package br.com.ponto.equilibrio.api.vo;

import br.com.ponto.equilibrio.api.model.JornadaTrabalho;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JornadaTrabalhoVO {

    private Long id;
    private LocalTime horaEntrada;
    private LocalTime horaSaida;
    private LocalTime intervaloInicio;
    private LocalTime intervaloFim;


    public JornadaTrabalhoVO(JornadaTrabalho jornadaTrabalho) {
        this.id = jornadaTrabalho.getId();
        this.horaEntrada = jornadaTrabalho.getHoraEntrada();
        this.horaSaida = jornadaTrabalho.getHoraSaida();
        this.intervaloInicio = jornadaTrabalho.getIntervaloInicio();
        this.intervaloFim = jornadaTrabalho.getIntervaloFim();
    }

}
