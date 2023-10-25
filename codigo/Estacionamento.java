import Exceptions.UsoDeVagaException;
import Exceptions.VagaDesocupadaException;
import Exceptions.VagaOcupadaException;

public class Estacionamento {

	private String nome;
	protected Cliente[] id;
	protected Vaga[] vagas;
	private int quantFileiras;
	private int vagasPorFileira;

	// Método Construtor
	public Estacionamento(String nome, int fileiras, int vagasPorFila, int maxClientes) {
		this.nome = nome;
		this.quantFileiras = fileiras;
		this.vagasPorFileira = vagasPorFila;
		this.id = new Cliente[maxClientes]; // Inicializa a matriz id com o tamanho desejado
		this.vagas = new Vaga[maxClientes * vagasPorFila];
		this.gerarVagas();
	}

	public String getnome() {
		return this.nome;
	}

	public int getquantFileiras() {
		return this.quantFileiras;
	}

	public int getvagasPorFileiras() {
		return this.vagasPorFileira;
	}

	public Cliente[] getClientes() {
		return this.id;
	}

	/***
	 * Adiciona um veículo no estacionamento.
	 * Percorre a lista de cliente com o for.
	 * No if se o id fornecido for igual ao do já armazenado ele adiciona o veículo
	 * ao estacionamento.
	 * 
	 * @param veiculo O veículo a ser adicionado
	 * @param idCli   A identificação do cliente através do seu id
	 */
	public void addVeiculo(Veiculo veiculo, String idCli) {
		for (Cliente cliente : id) {
			if (idCli.equals(cliente.getId())) {
				cliente.addVeiculo(veiculo);
			}
		}
	}

	/***
	 * Adiciona um cliente ao estacionamento tomando como base o tamanho do array de
	 * id's.
	 * 
	 * @param cliente O cliente a ser adicionado
	 */
	public void addCliente(Cliente cliente) {

		for (int i = 0; i < id.length; i++) {
			id[i] = cliente;
		}
	}

	/**
	 * Gera as vagas do estacionamento com base na quantidade de fileiras e vagas
	 * por fileira.
	 * As vagas geradas são armazenadas no array de vagas da classe.
	 */
	private void gerarVagas() {
		int vagaId = 1;
		for (int fila = 0; fila < quantFileiras; fila++) {
			for (int numero = 1; numero <= vagasPorFileira; numero++) {
				Vaga vaga = new Vaga(fila, numero);
				vagas[vagaId - 1] = vaga; // Subtrai 1 porque os índices de array começam em 0
				vagaId++;
			}
		}
	}

	/**
	 * Estaciona um veículo em uma vaga disponível
	 * Verifica se algum cliente possui um veículo com a placa especificada
	 * Estaciona o veículo em uma vaga disponível se o veículo é encontrado
	 * 
	 * @param placa A placa do veículo a ser estacionado.
	 * @throws VagaOcupadaException
	 */
	public boolean estacionar(String placa) throws VagaOcupadaException {
		boolean possuiVeiculo = false;
		boolean estacionado = false;
		for (int i = 0; i < id.length; i++) {
			if (id[i].possuiVeiculo(placa) != null)
				possuiVeiculo = true;
		}

		for (int i = 0; i < vagas.length; i++) {
			if (vagas[i].disponivel() && possuiVeiculo) {
				Veiculo veiculo = new Veiculo(placa);
				veiculo.estacionar(vagas[i]);
				estacionado = true;
			}

		}

		return estacionado;

	}

	/**
	 * Remove um veículo de uma vaga e calcula o valor pago pelo uso da vaga.
	 * 
	 * @param placa A placa do veículo a ser removido da vaga.
	 * @return O valor pago pelo uso da vaga ou -1 se o veículo não for encontrado
	 *         ou a operação falhar.
	 * @throws VagaDesocupadaException
	 * @throws UsoDeVagaException
	 */
	public double sair(String placa) throws UsoDeVagaException, VagaDesocupadaException {
		double valorPago = 0;
		for (Cliente cliente : id) {
			Veiculo veiculo = cliente.possuiVeiculo(placa);
			if (veiculo != null) {
				for (int i = 0; i < vagas.length; i++) {
					double valorVaga = veiculo.sair(vagas[i]);
					if (valorVaga != -1) {
						valorPago += valorVaga;
					}
				}
			}
		}
		return valorPago;
	}

	/**
	 * Calcula o valor total arrecadado com o estacionamento por todos os clientes.
	 * 
	 * @return O valor total arrecadado com o estacionamento.
	 */
	public double totalArrecadado() {
		double arrecadadoTotal = 0.0;
		for (Cliente cliente : id) {
			arrecadadoTotal += cliente.arrecadadoTotal();
		}
		return arrecadadoTotal;
	}

	/**
	 * Calcula o valor arrecadado no estacionamento em um mês específico.
	 * 
	 * @param mes O mês para o qual deseja calcular a arrecadação.
	 * @return O valor arrecadado no mês especificado.
	 */
	public double arrecadacaoNoMes(int mes) {
		double arrecadadoMes = 0.0;
		for (Cliente cliente : id) {
			arrecadadoMes += cliente.arrecadadoNoMes(mes);
		}
		return arrecadadoMes;
	}

	/***
	 * Calcula o valor médio gasto pelos clientes por uso das vagas de
	 * estacionamento.
	 * Ele itera através do array de clientes fornacido pelo id.
	 * Acumula no totalGasto o que foi arrecadado por cada cliente e no totalUsos o
	 * quanto foi usado por cada cliente.
	 * 
	 * @return O valor médio gasto por uso das vagas de estacionamento.
	 */
	public double valorMedioPorUso() {

		double totalGasto = 0.0;
		int totalUsos = 0;

		for (Cliente cliente : id) {
			if (cliente != null) {
				totalGasto += cliente.arrecadadoTotal();
				totalUsos += cliente.totalDeUsos();
			}
		}

		if (totalUsos == 0) {
			return 0.0;
		}

		return totalGasto / totalUsos;
	}

	/**
	 * Identifica os 5 melhores clientes que mais arrecadaram durante o mês
	 * estipulado
	 * utilizando um método para ordenar a lista dos clientes em ordem decrescente
	 * de arrecadação.
	 * 
	 * @param mes O mês para o qual deseja identificar o top 5.
	 * @return Uma String contendo o ID dos cinco melhores clientes de determinado
	 *         mês.
	 */
	public String top5Clientes(int mes) {
		Double top5[] = new Double[5];

		// Preenche o array top5 com as arrecadações dos 5 primeiros clientes.
		for (int i = 0; i < 5 && i < id.length; i++) {
			top5[i] = id[i].arrecadadoNoMes(mes);
		}

		// Ordena o array top5 em ordem decrescente.
		ordenacaoDecrescente(top5);

		// Crie uma string para armazenar os IDs dos cinco melhores clientes.
		StringBuilder result = new StringBuilder(mes);
		for (int i = 0; i < 5 && i < id.length; i++) {
			if (top5[i] != null) {
				result.append((i + 1) + ". " + id[i].getId() + "\n");
			}
		}
		return result.toString();
	}

	/**
	 * Ordena um array de Double em ordem decrescente.
	 *
	 * @param array O array a ser ordenado.
	 */
	private static void ordenacaoDecrescente(Double[] array) {
		int n = array.length;
		boolean troca;

		do {
			troca = false;

			for (int i = 0; i < n - 1; i++) {
				if (array[i] < array[i + 1]) {
					// Troca os elementos se estiverem fora de ordem (decrescente).
					double temp = array[i];
					array[i] = array[i + 1];
					array[i + 1] = temp;
					troca = true;
				}
			}
			// Reduz o tamanho do loop, já que o maior elemento está no final.
			n--;
		} while (troca);
	}
}
