import java.util.*;

import Enums.TipoCliente;

public class Cliente implements IDataToText {

	// Atributos
	private String nome;
	private String id;
	private TipoCliente tipo;
	private ArrayList<Veiculo> veiculos;

	/**
	 * Inicializador do construtor.
	 * 
	 * @param _nome
	 * @param _id
	 * @param _tipo
	 */
	private void init(String _nome, String _id, TipoCliente _tipo) {
		nome = _nome;
		id = _id;
		tipo = _tipo;
		veiculos = new ArrayList<Veiculo>();
	}

	/**
	 * Construtor que inicializa um cliente com nome, id e tipo personalizáveis.
	 * 
	 * @param _nome
	 * @param id2
	 * @param tipo
	 */
	public Cliente(String _nome, String id2, TipoCliente tipo) {
		init(_nome, id2, tipo);
	}

	/**
	 * Construtor que inicializa um cliente com nome anônimo, id neutro entre 0 e 1
	 * e tipo Horista.
	 * 
	 * @param _id
	 */
	public Cliente() {
		Random rand = new Random();
		int rangeId = 2;
		int idNeutro = rand.nextInt(rangeId);
		init("Anônimo", Integer.toString(idNeutro), TipoCliente.HORISTA);
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
	 * Método get para retornar o ID do cliente.
	 * 
	 * @return o ID do cliente.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Método get para retornar o tipo do cliente.
	 * 
	 * @return o tipo do cliente.
	 */
	public TipoCliente getTipoCliente() {
		return tipo;
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
	 * Método para verificar se o usuário possui um veículo com a placa
	 * correspondente.
	 * 
	 * @param placa
	 * @return O veículo correspondente se for encontrado, null caso contrário
	 */
	public Veiculo possuiVeiculo(String placa) {
		Veiculo veiculoBusca = new Veiculo(placa);
		return veiculos.stream()
				.filter(v -> v.equals(veiculoBusca))
				.findFirst()
				.orElse(null);
	}

	/**
	 * Método para retornar a quantidade registrada de uso de todos os veículos do
	 * cliente no estacionamento.
	 * 
	 * @return valor inteiro que indica o total de usos.
	 */
	public int totalDeUsos() {
		return veiculos.stream()
				.mapToInt(Veiculo::totalDeUsos)
				.sum();
	}

	/**
	 * Método que retorna a quantidade de usos do cliente em um mês específico.
	 * 
	 * @param mes mês escolhido.
	 * @return valor que indica o total de usos no mês.
	 */
	public int totalDeUsosMes(int mes) {
		return veiculos.stream()
				.mapToInt(v -> v.totalDeUsosNoMes(mes))
				.sum();
	}

	/**
	 * Método que retorna o total arrecadado de um veículo específico.
	 * 
	 * @param placa
	 * @return valor double que indica o total arrecadado daquele veículo.
	 */
	public double arrecadadoPorVeiculo(String placa) {
		Veiculo veiculoEx = new Veiculo(placa);
		return veiculos.stream()
				.filter(v -> v.equals(veiculoEx))
				.mapToDouble(Veiculo::totalArrecadado)
				.findFirst()
				.orElse(0.0);
	}

	/**
	 * Método que retorna o total de arrecadações de todos os veículos atribuídos ao
	 * cliente.
	 * 
	 * @return valor double que representa o total arrecadado de todos os veículos.
	 */
	public double arrecadadoTotal() {
		double arrecadacaoTotal = veiculos.stream()
				.mapToDouble(Veiculo::totalArrecadado)
				.sum();
		arrecadacaoTotal += tipo.getValor();
		return arrecadacaoTotal;
	}

	/**
	 * Método que retorna o total de arrecadações em um mês específico.
	 * 
	 * @param mes
	 * @return valor double que representa o total arrecadado naquele mês.
	 */
	public double arrecadadoNoMes(int mes) {
		double arrecadacaoNoMes = veiculos.stream()
				.mapToDouble(v -> v.arrecadadoNoMes(mes))
				.sum();
		arrecadacaoNoMes += tipo.getValor();
		return arrecadacaoNoMes;
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

	@Override
	public String dataToText() {
		return nome + ";" + id;
	}

}
