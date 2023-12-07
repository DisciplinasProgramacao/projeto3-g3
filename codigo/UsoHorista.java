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
        double valorTempo = calcularValorTempo();
        double valorServico = calcularValorServico();

        valorPago = valorTempo + valorServico;
        return valorPago;
    }
}
