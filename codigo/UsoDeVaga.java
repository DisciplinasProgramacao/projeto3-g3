import java.time.Duration;
import java.time.LocalDateTime;

public class UsoDeVaga {

	private static final double FRACAO_USO = 0.25;
	private static final double VALOR_FRACAO = 4.0;
	private static final double VALOR_MAXIMO = 50.0;
	private Vaga vaga;
	private LocalDateTime entrada;
	private LocalDateTime saida;
	private double valorPago;

	public UsoDeVaga(Vaga vaga) {
		this.vaga = vaga;
        this.entrada = LocalDateTime.now();

	}

	/**
	 * Registra o horário de saída do estacionamento e calcula o valor a ser pago com base no tempo de permanência.
	 *
	 * @return O valor a ser pago.
	 */
	public double sair() {
		saida = LocalDateTime.now();
		return valorPago();
	}

	/**
	 * Verifica se o uso da vaga ocorreu no mês especificado.
	 *
	 * @param mes O número do mês a ser verificado.
	 * @return Verdadeiro se o uso da vaga ocorreu no mês especificado, falso caso contrário.
	 */
	public boolean ehDoMes(int mes){
		if (saida == null) {
            return false;
        }
        return saida.getMonthValue() == mes;
    
	}

	/**
	 * Calcula o valor a ser pago com base no tempo de permanência na vaga.
	 *
	 * @return O valor a ser pago.
	 */
	public double valorPago() {
			Duration duracao = Duration.between(entrada, saida);
			long minutos = duracao.toMinutes();
			double valor = minutos / 15 * VALOR_FRACAO;

			if (valor > VALOR_MAXIMO) {
				valor = VALOR_MAXIMO;
			}

			valorPago = valor;

		return valorPago;
	}
}