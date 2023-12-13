import Enums.Turno;
import Exceptions.VagaOcupadaException;

/**
 * Representa o uso de uma vaga por um cliente de turno específico.
 */
public class UsoTurno extends UsoDeVaga {

    // #region atributos
    /**
     * O turno associado ao cliente durante o uso da vaga.
     */
    Turno turno;
    // #endregion

    // #region construtor
    /**
     * Construtor da classe UsoTurno.
     *
     * @param turno O turno associado ao cliente durante o uso da vaga.
     * @param vaga  A vaga utilizada durante o uso.
     * @throws VagaOcupadaException Se a vaga estiver ocupada.
     */
    public UsoTurno(Turno turno, Vaga vaga) throws VagaOcupadaException {
        super(vaga);

        this.turno = turno;
    }
    // #endregion

    // #region métodos de negócio

    /**
     * Calcula o valor pago pelo cliente de turno pelo uso da vaga.
     * Clientes de turno não pagam pelo uso dentro do seu turno.
     *
     * @return O valor pago pelo cliente de turno.
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
     * Verifica se o horário de entrada está dentro do turno associado ao cliente.
     *
     * @return true se estiver no turno, false caso contrário.
     */
    private boolean isHorarioTurno() {
        int horaEntrada = entrada.getHour();

        return (horaEntrada >= 8 && horaEntrada <= 12 && turno == Turno.MANHA)
                || (horaEntrada > 12 && horaEntrada <= 18 && turno == Turno.TARDE)
                || (horaEntrada > 18 && horaEntrada <= 23 && turno == Turno.NOITE);
    }
    // #endregion
}
