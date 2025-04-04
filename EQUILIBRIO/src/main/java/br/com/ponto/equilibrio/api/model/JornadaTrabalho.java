package br.com.ponto.equilibrio.api.model;

import br.com.ponto.equilibrio.api.vo.JornadaTrabalhoVO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "JORNADA_TRABALHO")
public class JornadaTrabalho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "HORA_ENTRADA", nullable = false)
    private LocalTime horaEntrada;
    @Column(name = "HORA_SAIDA", nullable = false)
    private LocalTime horaSaida;
    @Column(name = "INTERVALO_INICIO")
    private LocalTime intervaloInicio;
    @Column(name = "INTERVALO_FIM")
    private LocalTime intervaloFim;
    @Column(name = "BANCO_HORAS")
    private Boolean bancoHoras;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
@JoinTable(
    name = "jornada_funcionario",
    joinColumns = @JoinColumn(name = "jornada_id"),
    inverseJoinColumns = @JoinColumn(name = "funcionario_id")
)
private Set<Funcionario> funcionarios = new HashSet<>();

    public JornadaTrabalho(JornadaTrabalhoVO jornadaDeTrabalho) {
        this.id = jornadaDeTrabalho.getId();
        this.horaEntrada = jornadaDeTrabalho.getHoraEntrada();
        this.horaSaida = jornadaDeTrabalho.getHoraSaida();
        this.intervaloInicio = jornadaDeTrabalho.getIntervaloInicio();
        this.intervaloFim = jornadaDeTrabalho.getIntervaloFim();
        this.bancoHoras = jornadaDeTrabalho.getBancoDeHoras();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JornadaTrabalho that = (JornadaTrabalho) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
