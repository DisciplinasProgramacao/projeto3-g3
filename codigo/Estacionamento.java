

public class Estacionamento {

	private String nome;
	private Cliente[] id;
	private Vaga[] vagas;
	private int quantFileiras;
	private int vagasPorFileira; 

	//Método Construtor
	public Estacionamento(String nome, int fileiras, int vagasPorFila) {
		this.nome = nome;
		if (fileiras>26){ 
			this.quantFileiras = 26;
		} 
		else{ 
			this.quantFileiras = quantFileiras;
		} 
		if (vagasPorFila>99){ 
			this.quantFileiras = 99;
		} 
		else{ 
			this.vagasPorFileira = vagasPorFila;
		} 
		this.gerarVagas();
	}

	public String getnome() {
		return	this.nome;
	}

	public void setnome(String nome){
		this.nome = nome;
	}

	public int getquantFileiras(){
		return this.quantFileiras;
	}

	public void setquantFileiras(int quantFileiras){
		this.quantFileiras = quantFileiras;
	}

	public int getvagasPorFileiras(){
		return this.vagasPorFileira;
	}

	public void setvagasPorFileiras(int vagasPorFileira){
		this.vagasPorFileira = vagasPorFileira;
	}

	/***Adiciona um veículo vinculado ao id do cliente no estacionamento 
	 * 
	 * @param veiculo O veículo a ser adicionado
	 * @param idCli A identificação do cliente através do seu id
	 */
	public void addVeiculo(Veiculo veiculo, String idCli) {
		if(this.cliente.getid = idCli){
			this.cliente[clienteCerto].setVeiculo(veiculo);
				Veiculo[] veiculo = new Veiculo(String idCli);
		}
	}

	/*** Adiciona um cliente ao estacionamento.
	 * Ele verifica o tamanho do vetor Cliente através do id.lenght e adiciona mais um cliente ao final desse vetor
	 * @param cliente O cliente a ser adicionado
	 */
	public void addCliente(Cliente cliente) {
		this.id[this.id.length+1] = cliente;
	}

	/***
	 * 
	 */
	private void gerarVagas() {
		for(int i = 0; i < this.quantFileiras; i++){
			for(int j = 0; j < this.vagasPorFileira; j++){
				//O código ascendente da letra A + i e assim por diante acompanhado da quantVagas
				 //Vagas[vagas.lenght+1]= new Vaga(codigo asc, numero)
			}
		}
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
	 *
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
		StringBuilder result = new StringBuilder("Top 5 Clientes no Mês " + mes + ":\n");
		for (int i = 0; i < 5 && i < id.length; i++) {
			if (top5[i] != null) {
				result.append((i + 1) + ". " + id[i].getid() + "\n");
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
