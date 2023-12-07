import java.time.Duration;
import java.time.LocalDateTime;

import Exceptions.VagaOcupadaException;

public class UsoHorista extends UsoDeVaga {

    // Construtor
    public UsoHorista(Vaga vaga, LocalDateTime entrada) throws VagaOcupadaException {
        super(vaga);
        this.entrada = entrada;
    }

    /**
     * 
     * @return
     */
    public double calcularValorPago() {
        if (entrada != null && saida != null) {
            Duration duracao = Duration.between(entrada, saida);
            long minutosUsados = duracao.toMinutes();

            valorPago = calcularValorHorista(minutosUsados);

        } else {
            valorPago = 0.0;
        }

        return valorPago;
    }
}
