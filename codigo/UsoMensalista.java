import java.time.LocalDateTime;

import Enums.TipoCliente;
import Exceptions.VagaOcupadaException;

public class UsoMensalista extends UsoDeVaga {

    // #region construtor
    public UsoMensalista(Vaga vaga) throws VagaOcupadaException {
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
        double valorServico = calcularValorServico();
        TipoCliente mensalidade = TipoCliente.MENSALISTA;
        double totalAPagar = mensalidade.getValor();

        if(valorServico != 0){
            totalAPagar = valorServico + mensalidade.getValor();
        }



        return totalAPagar;
    }

    @Override
    public String toString() {
     return String.format("Uso Mensalista{vaga=%s, entrada=%s, saida=%s, valorPago=%.2f}",
            getVaga(), getEntrada(), getSaida(), getValorPago());
    }
    // #endregion

}