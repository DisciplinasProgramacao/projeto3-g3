import java.time.LocalDateTime;

import Exceptions.VagaOcupadaException;

public class UsoHorista extends UsoDeVaga {

    // Construtor
    public UsoHorista(Vaga vaga) throws VagaOcupadaException {
        super(vaga);
        this.entrada = LocalDateTime.now();
    }

    /**
     * 
     * @return
     */
    public double calcularValorPago() {
        // verificar se tem servico
        if (entrada != null && saida != null) {
            valorPago = calcularValorTempo();

        } else {
            valorPago = 0.0;
        }

        return valorPago;
    }
}
