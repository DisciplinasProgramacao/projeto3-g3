import java.time.LocalDateTime;

import Exceptions.VagaOcupadaException;

public class UsoHorista extends UsoDeVaga {

    // #region contrutor
    public UsoHorista(Vaga vaga) throws VagaOcupadaException {
        super(vaga);

        this.entrada = LocalDateTime.now();
    }
    // #endregion

    // #region métodos de negócio

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
    // #endregion
}
