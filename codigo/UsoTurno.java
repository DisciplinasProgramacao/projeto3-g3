import java.time.Duration;
import java.time.LocalDateTime;

import Enums.Turno;
import Exceptions.VagaOcupadaException;

public class UsoTurno extends UsoDeVaga {

    Turno turno;

    // Construtor
    public UsoTurno(Turno turno, Vaga vaga) throws VagaOcupadaException {
        super(vaga);
        this.turno = turno;
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
