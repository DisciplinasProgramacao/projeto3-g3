public class Estacionamento {

	private String nome;
	private Cliente[] id;
	private Vaga[] vagas;
	private int quantFileiras;
	private int vagasPorFileira; 

	//Método Construtor
	public Estacionamento(String nome, int fileiras, int vagasPorFila) {
			this.nome = nome;		
			this.quantFileiras = fileiras;
			this.vagasPorFileira = vagasPorFila;
			this.id = new Cliente[id.length];
			this.vagas = new Vaga[vagas.length];

		this.gerarVagas();
	}

	public String getnome() {
		return	this.nome;
	}

	public int getquantFileiras(){
		return this.quantFileiras;
	}

	public int getvagasPorFileiras(){
		return this.vagasPorFileira;
	}

	/*** Adiciona um veículo no estacionamento.
	 * Percorre a lista de cliente com o for. 
	 * No if se o id fornecido for igual ao do já armazenado ele adiciona o veículo ao estacionamento.
	 * @param veiculo O veículo a ser adicionado
	 * @param idCli A identificação do cliente através do seu id
	 */
	public void addVeiculo(Veiculo veiculo, String idCli) {
		for(Cliente cliente : id){
			if(idCLi.equals(cliente.getId)){
			cliente.addVeiculo(veiculo);
				}
			}
		}
	

	/*** Adiciona um cliente ao estacionamento tomando como base o tamanho do array de id's.
	 * @param cliente O cliente a ser adicionado
	 */
	public void addCliente(Cliente cliente) {
		
		for(int i=0; i < id.length; i++){
			id[i] = cliente;
		}
	}

	/**Gera as vagas do estacionamento com base na quantidade de fileiras e vagas por fileira.
	 * As vagas geradas são armazenadas no array de vagas da classe.
	 */
	private void gerarVagas() {
		
		// Calcula o número total de vagas com base na quantidade de fileiras e vagas por fileira.
		int totalVagas = quantFileiras * vagasPorFileira;

		// Inicializa o array de vagas com o tamanho total de vagas.
		vagas = new Vaga[totalVagas];
		}


	public void estacionar(String placa) {
		
	}

	public double sair(String placa) {
		
	}

	public double totalArrecadado() {
		
	}

	public double arrecadacaoNoMes(int mes) {

		
		
	}
	/***
	 * Calcula o valor médio gasto pelos clientes por uso das vagas de estacionamento.
	 * Ele itera através do array de clientes fornacido pelo id.
	 * Acumula no totalGasto o que foi arrecadado por cada cliente e no totalUsos o quanto foi usado por cada cliente.
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
	 * Identifica os 5 melhores clientes que mais arrecadaram durante o mês estipulado
	 * utilizando um método para ordenar a lista dos clientes em ordem decrescente de arrecadação.
	 * @param mes O mês para o qual deseja identificar o top 5.
	 * @return Uma String contendo o ID dos cinco melhores clientes de determinado mês.
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
