import java.util.*;

public class Cliente {

	private String nome;
	private String id;
	private ArrayList<Veiculo> veiculos;

	/**
	 * Inicializador do construtor.
	 * 
	 * @param _nome
	 * @param _id
	 */
	private void init(String _nome, String _id) {
		nome = _nome;
		id = _id;
		veiculos = new ArrayList<Veiculo>();
	}

	/**
	 * Construtor que inicializa um cliente com nome e id personalizável.
	 * 
	 * @param _nome
	 * @param _id
	 */
	public Cliente(String _nome, String _id) {
		init(_nome, _id);
	}

	/**
	 * Construtor que inicializa um cliente com nome anônimo e id neutro entre 0 e
	 * 1.
	 * 
	 * @param _id
	 */
	public Cliente() {
		Random rand = new Random();
		int rangeId = 2;
		int idNeutro = rand.nextInt(rangeId);
		init("Anônimo", Integer.toString(idNeutro));
	}

	/**
	 * Método para adicionar um veículo (ou vincular) ao cliente.
	 * 
	 * @param veiculo O veículo a ser adicionado.
	 */
	public void addVeiculo(Veiculo veiculo) {
		if (!veiculos.contains(veiculo)) {
			veiculos.add(veiculo);
		}
	}

	/**
	 * Método get para retornar o array de veículos registrados do cliente.
	 * 
	 * @return o array de veículos registrados do cliente.
	 */
	public ArrayList<Veiculo> getVeiculos() {
		return veiculos;
	}

	/**
	 * Método para verificar se o usuário possui um veículo com a placa
	 * correspondente.
	 * 
	 * @param placa
	 * @return O veículo correspondente se for encontrado, null caso contrário
	 */
	public Veiculo possuiVeiculo(String placa) {
		Veiculo veiculoBusca = new Veiculo(placa);
		for (Veiculo veiculo : veiculos) {
			if (veiculo.equals(veiculoBusca)) {
				return veiculo;
			}
		}
		return null;
	}

	/**
	 * Método para retornar a quantidade registrada de uso de todos os veículos do
	 * cliente no estacionamento.
	 * 
	 * @return valor inteiro que indica o total de usos.
	 */
	public int totalDeUsos() {
		int total = 0;
		for (Veiculo veiculo : veiculos) {
			total = veiculo.getUsos().size();
		}
		return total;
	}

	/**
	 * Método que retorna o total arrecadado de um veículo específico.
	 * 
	 * @param placa
	 * @return valor double que indica o total arrecadado daquele veículo.
	 */
	public double arrecadadoPorVeiculo(String placa) {
		double arrecadacaoVeiculo = 0;
		Veiculo veiculoEx = new Veiculo(placa);
		for (Veiculo veiculo : veiculos) {
			if (veiculo.equals(veiculoEx)) {
				arrecadacaoVeiculo = veiculoEx.totalArrecadado();
			}
		}
		return arrecadacaoVeiculo;
	}

	/**
	 * Método que retorna o total de arrecadações de todos os veículos atribuídos ao
	 * cliente.
	 * 
	 * @return valor double que representa o total arrecadado de todos os veículos.
	 */
	public double arrecadadoTotal() {
		double arrecadacaoTotal = 0;
		for (Veiculo veiculo : veiculos) {
			if (veiculo != null) {
				arrecadacaoTotal += veiculo.totalArrecadado();
			}
		}
		return arrecadacaoTotal;
	}

	/**
	 * Método que retorna o total de arrecadações em um mês específico.
	 * 
	 * @param mes
	 * @return valor double que representa o total arrecadado naquele mês.
	 */
	public double arrecadadoNoMes(int mes) {
		double arrecadadoNoMes = 0;
		for (Veiculo veiculo : veiculos) {
			arrecadadoNoMes = veiculo.arrecadadoNoMes(mes);
		}
		return arrecadadoNoMes;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Cliente) {
			Cliente cliente = (Cliente) obj;
			return cliente.id.equals(id);
		}
		return false;
	}

	@Override
	public String toString() {
		return "Cliente: " + nome + " | Id: " + id + " | Veículos: " + veiculos;
	}

}
