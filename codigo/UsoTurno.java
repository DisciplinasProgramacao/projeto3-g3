import java.time.Duration;
import java.time.LocalDateTime;

import Enums.Turno;

public class UsoTurno extends UsoDeVaga {

    // Construtor
    public UsoTurno(Turno turno, Vaga vaga, LocalDateTime entrada) {
        this.turno = turno;
        this.vaga = vaga;
        this.entrada = entrada;
    }

    /**
     * 
     * @return
     */
    public double calcularValorPago() {
        if (isHorarioTurno()) {
            return 0.0; // Cliente de turno não paga pelo uso dentro do seu turno
        }
        
        Duration duracao = Duration.between(entrada, saida);
        long minutosUsados = duracao.toMinutes();
            
        valorPago = calcularValorHorista(minutosUsados);

        return valorPago;
    }

    /**
     * 
     * @return
     */
    private boolean isHorarioTurno() {
        int horaEntrada = entrada.getHour();

        return (horaEntrada >= 8 && horaEntrada <= 12 && turno == Turno.MANHA)
                || (horaEntrada > 12 && horaEntrada <= 18 && turno == Turno.TARDE)
                || (horaEntrada > 18 && horaEntrada <= 23 && turno == Turno.NOITE);
    }
}
