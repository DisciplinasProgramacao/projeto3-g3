import Enums.Turno;
import Exceptions.VagaOcupadaException;

public class UsoTurno extends UsoDeVaga {

    //#region atributos
    Turno turno;
    //#endregion

    // #region construtor
    public UsoTurno(Turno turno, Vaga vaga) throws VagaOcupadaException {
        super(vaga);

        this.turno = turno;
    }
    //#endregion

    // #region métodos de negócio

    /**
     * Cliente de turno não paga pelo uso dentro do seu turno
     * 
     * @return
     */
    public double calcularValorPago() {
        double valorTempo = calcularValorTempo();
        double valorServico = calcularValorServico();

        if (isHorarioTurno()) {
            return valorServico;
        }

        valorPago = valorTempo + valorServico;
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

    @Override
    public String toString() {
     return String.format("Uso Turno{vaga=%s, entrada=%s, saida=%s, valorPago=%.2f}",
            getVaga(), getEntrada(), getSaida(), getValorPago());
    }
    // #endregion
}
