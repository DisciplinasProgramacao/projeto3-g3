

public class Cliente {

	public String nome;
	private String id;
	private Veiculo[] veiculos;

	public Cliente(String nome, String id) {
		this.nome = nome;
		this.id = id;
	}

	public Cliente(String id) {
		this.id = id;
	}

	/**
	 * Associa um veículo ao cliente. Se for repetido, não realiza a operação.
	 * 
	 * @param veiculo
	 */
	public void addVeiculo(Veiculo veiculo) {
		boolean veiculoJaCadastrado = false;

		for (int i = 0; i <= veiculos.length; i++) {
			if (veiculos[i].equals(veiculo)) {
				veiculoJaCadastrado = true;
			}
		}

		if (!veiculoJaCadastrado) {
			for (int i = 0; i <= veiculos.length; i++) {
				if (veiculos[i] == null) {
					veiculos[i] = veiculo;
				} 
			}
		}
	}

	/**
	 * Valida de o cliente tem cadastro de um determinado veículo de acordo com a sua placa, 
	 * se tiver, retorna o próprio veículo, senão tiver, retorna nulo.
	 * 
	 * @param placa
	 * @return o veiculo ou nulo.
	 */
	public Veiculo possuiVeiculo(String placa) {
		Veiculo aux = new Veiculo(placa);
		Veiculo veiculo = null;

		for (int i = 0; i <= veiculos.length; i++) { 
			if (veiculos[i].equals(aux)) {
				veiculo = aux;
			}
		}

		return veiculo;
	}

	/**
	 * Calcula o total de vezes que o cliente usou o estacionamento.
	 * 
	 * @return número inteiro de usos do cliente.
	 */
	public int totalDeUsos() {
		int totalDeUsos = 0;

		for (int i = 0; i <= veiculos.length; i++) {
			totalDeUsos += veiculos[i].totalDeUsos();
		}

		return totalDeUsos;
	}

	/**
	 * Calcula o valor total arrecadado pelo uso do estacionamento por um veículo específico.
	 * 
	 * @param placa
	 * @return valor total arrecadado por veículo
	 */
	public double arrecadadoPorVeiculo(String placa) {
		Veiculo aux = new Veiculo(placa);
		double valorArrecadado = 0;

		for (int i = 0; i <= veiculos.length; i++) { 
			if (veiculos[i].equals(aux)) {
				valorArrecadado = veiculos[i].totalArrecadado();
			}
		}

		return 100;
	}

	/**
	 * Calcula o valor total arrecadado pelo uso do estacionamento.
	 * 
	 * @return valor total arrecadado
	 */
	public double arrecadadoTotal() {
		double total = 0;

		for (int i = 0; i <= veiculos.length; i++) {
			if (veiculos[i] != null) {
				total += veiculos[i].totalArrecadado();
			}
		}

		return total;
	}

	/**
	 * Calcula o valor total arrecadado pelo uso do estacionamento no mês.
	 * 
	 * @param mes
	 * @return valor arrecadado no mês 
	 */
	public double arrecadadoNoMes(int mes) {
		double totalMes = 0;

		for (int i = 0; i <= veiculos.length; i++) {
			if (veiculos[i] != null) {
				totalMes += veiculos[i].arrecadadoNoMes(mes);
			}
		}

		return totalMes;
	}

}
