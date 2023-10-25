import java.time.Duration;
import java.time.LocalDateTime;

import Enums.Servicos;
import Exceptions.*;

public class UsoDeVaga {

	private static final double FRACAO_USO = 0.25;
	private static final double VALOR_FRACAO = 4.0;
	private static final double VALOR_MAXIMO = 50.0;
	private Vaga vaga;
	private LocalDateTime entrada;
	private LocalDateTime saida;
	private double valorPago;
	private Servicos servico;

	/**
	 * Inicializa uma vaga sem serviço.
	 * 
	 * @param vaga Vaga a ser ocupada.
	 */
	public UsoDeVaga(Vaga vaga) throws VagaOcupadaException {
		init(vaga, null);
	}

	/**
	 * Inicializa uma vaga com serviço.
	 * 
	 * @param vaga    Vaga a ser ocupada.
	 * @param servico Serviço a ser contratado.
	 */
	public UsoDeVaga(Vaga vaga, Servicos servico) throws VagaOcupadaException {
		init(vaga, servico);
	}

	/**
	 * Construtor padrão da classe UsoDeVaga.
	 * 
	 * @param vaga
	 * @param servico
	 * @throws VagaOcupadaException
	 */
	private void init(Vaga vaga, Servicos servico) throws VagaOcupadaException {
		this.vaga = vaga;
		this.servico = servico;
		this.entrada = LocalDateTime.now();

		if (vaga.disponivel()) {
			vaga.estacionar();
		} else {
			throw new VagaOcupadaException("A vaga já está sendo ocupada por outro veículo.");
		}
	}

	/**
	 * Método que retorna o horário de entrada do veículo na vaga.
	 * 
	 * @return entrada
	 */
	public LocalDateTime getEntrada() {
		return entrada;
	}

	/**
	 * Método que retorna o horário de saída do veículo na vaga.
	 * 
	 * @return saida
	 */
	public LocalDateTime getSaida() {
		return saida;
	}

	/**
	 * Método que retorna a vaga.
	 * 
	 * @return vaga
	 */
	public Vaga getVaga() {
		return vaga;
	}

	/**
	 * Método que retorna o valor pago pelo uso da vaga.
	 * 
	 * @return valorPago
	 */
	public double getValorPago() {
		return valorPago;
	}

	/**
	 * Método que contrata um serviço escolhido pelo cliente.
	 * 
	 * @param servicoEscolhido o serviço a ser contratado.
	 */
	public void contratarServico(Servicos servicoEscolhido) {
		this.servico = servicoEscolhido;
	}

	/**
	 * Registra o horário de saída do estacionamento e calcula o valor a ser pago
	 * com base no tempo de permanência.
	 *
	 * @return O valor a ser pago.
	 * @throws UsoDeVagaException
	 * @throws VagaDesocupadaException
	 */
	public double sair() throws UsoDeVagaException, VagaDesocupadaException {
		if (this.saida != null) {
			throw new UsoDeVagaException("Veículo já saiu da vaga.");
		}
		if (!podeSair(LocalDateTime.now())) {
			throw new VagaDesocupadaException(
					"A vaga não pode ser desocupada porque o serviço de " + servico.getServicodeDesc()
							+ " ainda não foi concluído. O tempo mínimo de permanência é de " + servico.getTempoMinimo() + " horas.");
		}
		this.saida = LocalDateTime.now();
		double valorPago = calcularValorPago();
		return valorPago;
	}

	/**
	 * Método privado que verifica se o tempo mínimo do serviço contratado já foi
	 * concluído para liberação da vaga.
	 * 
	 * @return true se o tempo mínimo do serviço contratado já foi concluído,
	 *         false caso contrário.
	 */
	private boolean podeSair(LocalDateTime saida) {
		if (servico == null) {
			return true;
		}
		Duration tempoMinimo = Duration.between(this.entrada, saida);
		return tempoMinimo.toHours() >= this.servico.getTempoMinimo();
	}

	/**
	 * Verifica se o uso da vaga ocorreu no mês especificado.
	 *
	 * @param mes O número do mês a ser verificado.
	 * @return Verdadeiro se o uso da vaga ocorreu no mês especificado, falso caso
	 *         contrário.
	 */
	public boolean ehDoMes(int mes) {
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
	public double calcularValorPago() {
		if (this.saida == null) {
			return 0.0;
		}

		Duration duracao = Duration.between(this.entrada, this.saida);
		double minutos = duracao.toMinutes();
		double valorAPagar = (minutos / 15) * VALOR_FRACAO;
		System.out.print("ValorAPagar: " + valorAPagar);

		if (valorAPagar > VALOR_MAXIMO) {
			valorAPagar = VALOR_MAXIMO;
		}
		if (this.servico != null) {
			valorAPagar += servico.getValor();
		}
		this.valorPago = valorAPagar;

		return this.valorPago;
	}
}