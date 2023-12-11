import java.time.LocalDateTime;

import Exceptions.VagaOcupadaException;

public class UsoMensalista extends UsoDeVaga {

    //#region construtor
    public UsoMensalista(Vaga vaga) throws VagaOcupadaException {
        super(vaga);

        this.entrada = LocalDateTime.now();
    }
    //#endregion

    //#region métodos de negócio

    /**
     * 
     * @return
     */
    public double calcularValorPago() {
        double valorServico = calcularValorServico();

        return valorServico;
    }
    //#endregion
}