import java.time.LocalDateTime;

public class UsoMensalista extends UsoDeVaga {

    // Construtor
    public UsoMensalista(Vaga vaga, LocalDateTime entrada) {
        this.vaga = vaga;
        this.entrada = entrada;
    }

    /**
     * 
     * @return
     */
    public double calcularValorPago() {
        valorPago = 0.0;

        return valorPago;
    }
}