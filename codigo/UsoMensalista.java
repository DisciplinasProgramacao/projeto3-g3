import java.time.LocalDateTime;

import Exceptions.VagaOcupadaException;

public class UsoMensalista extends UsoDeVaga {

    // Construtor
    public UsoMensalista(Vaga vaga, LocalDateTime entrada) throws VagaOcupadaException {
        super(vaga);
        this.entrada = entrada;
    }

    /**
     * 
     * @return
     */
    public double calcularValorPago() {
        valorPago = 0.0;
        // verificar se tem servico
        return valorPago;
    }
}